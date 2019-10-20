/* Copyright 2016 Roychoudhury, Abhishek */

package org.verizon.dmsservices.service;

/**
 * Defines the Business Logic for basic File Manipulation.
 * 
 * @author abhishek
 * @since 1.0
 */
public interface NuxeoRestService {

    /**
     * Uploads file into nuxeo. 
     * @param fileName
     * @throws Exception
     */
    void uploadFile(String fileName) throws Exception;

}
