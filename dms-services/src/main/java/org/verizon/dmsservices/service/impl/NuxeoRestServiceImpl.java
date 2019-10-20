/* Copyright 2016 Roychoudhury, Abhishek */

package org.verizon.dmsservices.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.verizon.common.enums.NuxeoFileUploadState;
import org.verizon.dmsservices.command.NuxeoFileUploadCommand;
import org.verizon.dmsservices.service.NuxeoRestService;

/**
 * Implementation class for NuxeoRestService
 * @author abhishek
 * @since  1.0
 */
@Service
public class NuxeoRestServiceImpl implements NuxeoRestService {
	private Logger logger = LoggerFactory.getLogger(NuxeoFileUploadCommand.class);

    @Value("${app.dmsservices.location}")
    private String uploadLocation;
    @Value("${app.dmsservices.vrddocuments}")
    private String vrdDocuments;
    @Value("${app.dmsservices.legacydocuments}")
    private String legacyDocuments;

	/* (non-Javadoc)
	 * @see org.verizon.dmsservices.service.NuxeoRestService#uploadFile(java.lang.String)
	 */
	public void uploadFile(String fileName) {
		String finalLocation = this.uploadLocation + fileName;
		NuxeoFileUploadCommand command = new NuxeoFileUploadCommand(finalLocation, this.vrdDocuments);
		NuxeoFileUploadState state = command.execute();
		logger.debug("File {} upload ended with message : {}", fileName, state);
	}
}
