package org.verizon.documentservices.services;

import java.util.List;
import java.util.Map;

import org.verizon.documentservices.model.Document;

/**
 * This service layer class contains all the possible document services
 * 
 * @author Shankar K
 *
 */
public interface DocumentService {

	/**
	 * @param docName the docName to find
	 * @return Document 
	 */
	Document findByDocuName(String docName);

	/**
	 * @param docType the docType to find
	 * @return List<Document> 
	 */
	List<Document> findByDocuType(String docType);

	/**
	 * @param docType the docName to find
	 * @return List<Document> 
	 */
	List<Document> findByDocuNamePattern(String docName);
	
	/**
	 * @param docType the docType to find
	 * @return List<Document> 
	 */
	List<Document> findByDocuTypePattern(String docType);
	
	/**
	 * @param user the docType to user
	 * @return List<Document> 
	 */
	List<Document> findDocuByUser(String user);
	
	/**
	 * @param group the docType to group
	 * @return List<Document> 
	 */
	List<Document> findDocuByGroup(String group);
	
	/**
	 * @param category the docType to category
	 * @return List<Document> 
	 */
	List<Document> findDocuByCategory(String category);

	/**
	 * @param pattern the pattern to extract metadata
	 * @return List<Document> 
	 */
	List<Document> extractMetaData(String pattern);

	/**
	 * @param Map<String, String> payload the payload containing metadata to store
	 * @return List<Document> 
	 */
	String storeMetaData(Map<String, String> payload);

	/**
	 * @param tags the tags to find
	 * @return List<Document> 
	 */
	List<Document> findByDocuTags(String tags);

	/**
	 * @param searchPattern the searchPattern containing filters and keyword to search
	 * @return List<Document> 
	 */
	List<Document> findAllDocumentsBySearchPattern(String searchPattern);

	/**
	 * @param Map<String, String> payload the payload containing filters and keyword to search
	 * @return List<Document> 
	 */
	List<Document> findDocumentsByFilteredSearchPattern(Map<String, String> payload);

}
