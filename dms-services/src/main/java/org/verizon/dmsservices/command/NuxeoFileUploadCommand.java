package org.verizon.dmsservices.command;

import java.io.File;
import java.util.Map;

import org.nuxeo.java.client.api.ConstantsV1;
import org.nuxeo.java.client.api.NuxeoClient;
import org.nuxeo.java.client.api.objects.Document;
import org.nuxeo.java.client.api.objects.upload.BatchUpload;
import org.nuxeo.java.client.api.objects.user.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.verizon.common.enums.NuxeoFileUploadState;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * @author abhishek
 *
 */
public class NuxeoFileUploadCommand extends HystrixCommand<NuxeoFileUploadState>  {
	private Logger logger = LoggerFactory.getLogger(NuxeoFileUploadCommand.class);

	private String baseURL;
	private String userName;
	private String password;
	private NuxeoClient nuxeoClient;

	private String filePath;
	private String folderId;

	/**
	 * @param filePath
	 * @param folderId
	 */
	public NuxeoFileUploadCommand(String filePath, String folderId) {
		super(HystrixCommandGroupKey.Factory.asKey("GmailSMTPEmailSender"));
		this.baseURL = "http://localhost:8080/nuxeo";
		this.userName = "Administrator";
		this.password = "Administrator";
		this.filePath = filePath;
		this.folderId = folderId;
	}

	/* (non-Javadoc)
	 * @see com.netflix.hystrix.HystrixCommand#run()
	 */
	@Override
	protected NuxeoFileUploadState run() throws Exception {
		this.login().batchUpload();
		return NuxeoFileUploadState.UPLOADED;
	}

	/* (non-Javadoc)
	 * @see com.netflix.hystrix.HystrixCommand#getFallback()
	 */
	@Override
	protected NuxeoFileUploadState getFallback() {
		return NuxeoFileUploadState.FAILED;
	}

	/**
	 * @return
	 */
	private NuxeoFileUploadCommand login() {
		this.nuxeoClient = new NuxeoClient(this.baseURL, this.userName, this.password)
				.timeout(60)
				.header(ConstantsV1.HEADER_PROPERTIES, "*");

		CurrentUser currentUser = this.nuxeoClient.fetchCurrentUser();
		logger.info("Connection succeeded for user : {}", currentUser.getUsername());

		return this;
	}

	/**
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private NuxeoFileUploadCommand batchUpload() {
		File file = new File(this.filePath);
		// Upload file chunks
		BatchUpload batchUpload = this.nuxeoClient.fetchUploadManager();
		batchUpload = batchUpload.upload(file.getName(), file.length(), "txt", batchUpload.getBatchId(), "1", file);
		logger.info("batchUpload : {}", batchUpload);

		// Getting a doc and attaching the batch file
		Document doc = new Document("file", "DM_File");
		doc.set("dc:title", "new title");
		doc = this.nuxeoClient.repository().createDocumentById(this.folderId, doc);
		doc.set("file:content", batchUpload.getBatchBlob());
		doc = doc.updateDocument();
		logger.warn("File uploaded successfully : {}", ((Map) doc.get("file:content")).get("name"));

		return this;
	}
}
