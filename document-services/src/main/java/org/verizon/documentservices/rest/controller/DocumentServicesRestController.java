package org.verizon.documentservices.rest.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.verizon.documentservices.model.Document;
import org.verizon.documentservices.services.DocumentService;

/**
 * Rest controller in charge of servicing the document service related requests
 * 
 * @author Shankar K
 *
 */
@RestController
@RequestMapping("/documentservices")
public class DocumentServicesRestController {

	private final DocumentService documentService;

	/**
	 * This method retrieves all the documents matching the given docName
	 * 
	 * @param docName
	 * @return List<Document> returns the list of documents for the docName given
	 */
	@RequestMapping(value = "/name/{docName}", method = RequestMethod.POST)
	List<Document> retrieveDocuByName(@PathVariable String docName) {
		return this.documentService.findByDocuNamePattern(docName);
	}
	
	/**
	 * This method retrieves all the documents matching the given docType
	 * 
	 * @param docType
	 * @return List<Document> returns the list of documents for the docType given
	 */
	@RequestMapping(value = "/type/{docuType}", method = RequestMethod.POST)
	List<Document> retrieveDocuByType(@PathVariable String docType) {
		return this.documentService.findByDocuTypePattern(docType);
	}
	
	/**
	 * This method retrieves all the documents matching the given category
	 * 
	 * @param category of the document
	 * @return List<Document> returns the list of documents for the category given
	 */
	@RequestMapping(value = "/category/{docCategory}", method = RequestMethod.POST)
	List<Document> retrieveDocuByCategory(@PathVariable String docCategory) {
		return this.documentService.findDocuByCategory(docCategory);
	}
	
	/**
	 * This method retrieves all the documents matching the given user
	 * 
	 * @param user
	 * @return List<Document> returns the list of documents for the user given
	 */
	@RequestMapping(value = "/user/{user}", method = RequestMethod.POST)
	List<Document> retrieveDocuByUser(@PathVariable String user) {
		return this.documentService.findDocuByUser(user);
	}
	
	/**
	 * This method retrieves all the documents matching the given group
	 * 
	 * @param group
	 * @return List<Document> returns the list of documents for the group given
	 */
	@RequestMapping(value = "/group/{group}", method = RequestMethod.POST)
	List<Document> retrieveDocuByGroup(@PathVariable String group) {
		return this.documentService.findDocuByGroup(group);
	}
	
	/**
	 * This method retrieves all the documents matching the given tags
	 * 
	 * @param tags
	 * @return List<Document> returns the list of documents for the tags given
	 */
	@RequestMapping(value = "/tags/{tags}", method = RequestMethod.POST)
	List<Document> retrieveDocuByTags(@PathVariable String tags) {
		return this.documentService.findByDocuTags(tags);
	}
	
	/**
	 * This method extracts the document MetaData which matches with teh pattern used
	 * 
	 * @param pattern
	 * @return List<Document> returns the list of documents for the pattern used to extractMetaData
	 */
	@RequestMapping(value = "/extractMetaData/{pattern}", method = RequestMethod.POST)
	List<Document> extractMetaData(@PathVariable String pattern) {
		return this.documentService.extractMetaData(pattern);
	}

	/**
	 * This method allows to store the meta data information of the documents.
	 * 
	 * Sample input for usage:
	 * 
	 * {
			"docId" : "123abc",
			"docName" : "share info",
			"docType" : "pdf",
			"docState" : "approved",
			"docTitle" : "Docu Title",
			"docDescription" : "This is a test description",
			"majorVersion" : "10",
			"minorVersion" : "9",
			"garmLevel" : "16",
			"gsamLevel" : "888",
			"source" : "VzK",
			"content" : "Actual file content of the document",
			"tags" : "[ #sjkghak, #asas ]",
			"language" : "English",
			"user" : "Tester",
			"category" : "test",
			"group" : "user group belonging to",
			"userEmail" : "test@test.com",
			"mimeType" : "",
			"creator" : "name of the creator",
			"lastContributor" : "last updated user",
			"createdAt" : "02/23/2015 06:35:56",
			"lastModifiedAt" : "02/23/2015 06:35:56",
			"expiresAt" : "02/23/2015 06:35:56",
			"size" : "138 KB"
		}
	 *  
	 * @param payload
	 * @return String representing the status of this operation
	 */
	@RequestMapping(value = "/storeMetaData", method = RequestMethod.POST)
	String storeMetaData(@RequestBody Map<String, String> payload) {
		return this.documentService.storeMetaData(payload);
	}
	
	/**
	 * This method allows to search for a keyword in the complete system
	 * 
	 * @param searchPattern
	 * @return List<Document> returns the list of documents for the search keyword used
	 */
	@RequestMapping(value = "/search/{searchPattern}", method = RequestMethod.POST)
	List<Document> findAllDocumentsBySearchPattern(@PathVariable String searchPattern) {
		return this.documentService.findAllDocumentsBySearchPattern(searchPattern);
	}
	
	/**
	 * This method allows to search for a keyword in the complete system by multiple filters
	 * 
	 * Sample input usage:
	 * 
	 * {
			"searchString" : "any string to search",
			"filterTypes" : "docType#docName#docType"
	 * }
	 * 
	 * @param payload
	 * @return List<Document> returns the list of documents for the search keyword used
	 */
	@RequestMapping(value = "/searchByFilters/{searchPattern}", method = RequestMethod.POST)
	List<Document> findDocumentsByFilteredSearchPattern(@RequestBody Map<String, String> payload) {
		return this.documentService.findDocumentsByFilteredSearchPattern(payload);
	}

	@Autowired
	DocumentServicesRestController(DocumentService documentService) {
		this.documentService = documentService;
	}
}
