/* Copyright 2016 Roychoudhury, Abhishek */

package org.verizon.dmsservices.service;

import java.io.File;

import org.verizon.common.dataobjects.Document;


/**
 * Defines the Business Logic for basic File Manipulation.
 * 
 * @author abhishek
 * @since 1.0
 */
public interface FileManipulationService {

    /**
     * Uploads file to Nuxeo
     * @param fileName
     * @param bytes
     * @return String
     * @throws Exception
     */
    String uploadFile(String fileName, byte[] bytes) throws Exception;

    /**
     * makes health check
     * @return File
     * @throws Exception
     */
    File testDownloadFile() throws Exception;

    /**
     * Downloads file from Nuxeo
     * @param docId
     * @return Document
     * @throws Exception
     */
    Document downloadFile(String docId) throws Exception;

}
