/* Copyright 2016 Roychoudhury, Abhishek */

package org.verizon.dmsservices.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.verizon.common.dataobjects.Document;
import org.verizon.dmsservices.service.FileManipulationService;

/**
 * Implementation class for FileManipulationService
 * @author abhishek
 * @since  1.0
 */
@Service
public class FileManipulationServiceImpl implements FileManipulationService {
    @Value("${app.dmsservices.location}")
    private String uploadLocation;
    private String lastUploadedFileName;

    /**
     * @author abhishek
     * @since  1.0
     * @see org.verizon.dmsservices.service.FileManipulationService#uploadFile()
     */
    public String uploadFile(String fileName, byte[] bytes) throws Exception {
        String finalLocation = this.uploadLocation + File.separator + fileName;
        this.lastUploadedFileName = fileName;
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(finalLocation)));
        stream.write(bytes);
        stream.close();
        return "You successfully uploaded " + fileName + "!";
    }

    /**
     * @author abhishek
     * @since  1.0
     * @see org.verizon.dmsservices.service.FileManipulationService#testDownloadFile(java.lang.String)
     */
    public File testDownloadFile() throws Exception {
        return new File(this.uploadLocation + File.separator + this.lastUploadedFileName);
    }

    /**
     * @param docId
     * @return Document
     * @throws Exception
     */
    public Document downloadFile(String docId) throws Exception {
    	Document document = new Document();
    	// how to get the physical file???
    	document.setMimeType("application/pdf");
    	document.setName(this.lastUploadedFileName);
    	document.setPhysicalFile(new File(this.uploadLocation + File.separator + this.lastUploadedFileName));
    	return null;
    }

}
