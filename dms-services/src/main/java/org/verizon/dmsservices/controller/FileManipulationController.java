/* Copyright 2016 Roychoudhury, Abhishek */

package org.verizon.dmsservices.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.verizon.common.dataobjects.Document;
import org.verizon.common.dataobjects.ServerResponse;
import org.verizon.common.enums.ResponseCode;
import org.verizon.common.util.UniqueTokenUtils;
import org.verizon.dmsservices.service.FileManipulationService;

/**
 * Controller for basic file manipulation in terms of uploading and downloading
 * files.
 * 
 * @author abhishek
 * @since 1.0
 */
@Controller
public class FileManipulationController {
	private Logger logger = LoggerFactory.getLogger(FileManipulationController.class);

    @Autowired
    private FileManipulationService service;

    /**
     * Takes care of health check
     * @param file
     * @return ServerResponse
     */
    @RequestMapping(value = "/testupload", method = RequestMethod.POST)
    public @ResponseBody ServerResponse<String> handleTestFileUpload(@RequestParam("file") MultipartFile file) {
        String name = file.getOriginalFilename();
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                return new ServerResponse<String>(
                        ResponseCode.SUCCESS, service.uploadFile(name, bytes));
            } catch (Exception e) {
                return new ServerResponse<String>(
                        ResponseCode.FAILURE, "You failed to upload " + name + " => " + e.getMessage());
            }
        } else {
            return new ServerResponse<String>(
                    ResponseCode.FAILURE, "You failed to upload " + name + " because the file/name was empty.");
        }
    }

    /**
     * Uploads file into nuxeo. 
     * @param file
     * @param userSessionId
     * @return ServerResponse
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody ServerResponse<String> handleFileUpload(
    		@RequestParam("file") MultipartFile file,
    		@RequestHeader(value="X-DMS-SESSIONID", defaultValue="NONE") String userSessionId) {
        String name = file.getOriginalFilename();
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                return new ServerResponse<String>(
                        ResponseCode.SUCCESS, service.uploadFile(name, bytes));
            } catch (Exception e) {
                return new ServerResponse<String>(
                        ResponseCode.FAILURE, "You failed to upload " + name + " | Error : " + e.getMessage());
            }
        } else {
            return new ServerResponse<String>(
                    ResponseCode.FAILURE, "You failed to upload " + name + " because the file/name was empty.");
        }
    }

    /**
     * Takes of health check
     * @param id
     * @param response
     * @return FileSystemResource
     */
    @RequestMapping(value="/testdownload/{id}", method=RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody FileSystemResource handleTestFileDownload(@PathVariable(value="id") String id, HttpServletResponse response) {
        try {
            response.setContentType("application/png");
            response.setHeader("Content-Disposition", "attachment; filename=somefile.png");
            return new FileSystemResource(this.service.testDownloadFile());
        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("application/png");
            response.setHeader("Content-Disposition", "attachment; filename=error-in-download.txt");
            return new FileSystemResource("/utility/error-in-download.txt");
        }
    }

    /**
     * Downloads the file from nuxeo
     * @param id
     * @param userId
     * @param userSessionId
     * @param response
     * @return FileSystemResource
     */
    @RequestMapping(value="/download/{id}", method=RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody FileSystemResource handleFileDownload(
    		@PathVariable(value="id") String id,
    		@RequestHeader(value="X-DMS-LOGGEDUSERID", defaultValue="NONE") String userId,
    		@RequestHeader(value="X-DMS-SESSIONID", defaultValue="NONE") String userSessionId,
    		HttpServletResponse response) {
        try {
        	if (UniqueTokenUtils.validateUserToken(userId, userSessionId)) {
        		Document document = this.service.downloadFile(id);
        		response.setContentType(document.getMimeType());
                response.setHeader("Content-Disposition", "attachment; filename=" + document.getName());
                return new FileSystemResource(document.getPhysicalFile());
        	} else {
        		logger.error("User not logged in !!!");
        		response.setContentType("application/txt");
                response.setHeader("Content-Disposition", "attachment; filename=error-in-download.txt");
        		return new FileSystemResource("/utility/error-in-download.txt");
        	}
        } catch (Exception e) {
        	logger.error("Exception occurred in processing {}", e);
    		response.setContentType("application/txt");
            response.setHeader("Content-Disposition", "attachment; filename=error-in-download.txt");
            return new FileSystemResource("/utility/error-in-download.txt");
        }
    }
}
