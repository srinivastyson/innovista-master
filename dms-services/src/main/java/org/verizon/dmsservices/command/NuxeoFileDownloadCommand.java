package org.verizon.dmsservices.command;

import org.nuxeo.java.client.api.ConstantsV1;
import org.nuxeo.java.client.api.NuxeoClient;
import org.nuxeo.java.client.api.objects.Document;
import org.nuxeo.java.client.api.objects.user.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * @author abhishek
 *
 */
public class NuxeoFileDownloadCommand extends HystrixCommand<Document> {
	private Logger logger = LoggerFactory.getLogger(NuxeoFileDownloadCommand.class);

	private String baseURL;
	private String userName;
	private String password;
	private NuxeoClient nuxeoClient;

	private String fileId;

	/**
	 * @param fileId
	 */
	public NuxeoFileDownloadCommand(String fileId) {
		super(HystrixCommandGroupKey.Factory.asKey("NuxeoFileDownloadCommand"));
		this.baseURL = "http://localhost:8080/nuxeo";
		this.userName = "Administrator";
		this.password = "Administrator";
		this.fileId = fileId;
	}

	/* (non-Javadoc)
	 * @see com.netflix.hystrix.HystrixCommand#run()
	 */
	@Override
	protected Document run() throws Exception {
		this.login();
		return nuxeoClient.repository("default").fetchDocumentById(this.fileId);
	}

	/* (non-Javadoc)
	 * @see com.netflix.hystrix.HystrixCommand#getFallback()
	 */
	@Override
	protected Document getFallback() {
		Document document = new Document();
		document.setId("null");
		document.setName("error.txt");
		return document;
	}

	private NuxeoFileDownloadCommand login() {
		this.nuxeoClient = new NuxeoClient(this.baseURL, this.userName, this.password).timeout(60)
				.header(ConstantsV1.HEADER_PROPERTIES, "*");

		CurrentUser currentUser = this.nuxeoClient.fetchCurrentUser();
		logger.info("Connection succeeded for user : {}", currentUser.getUsername());

		return this;
	}
}
