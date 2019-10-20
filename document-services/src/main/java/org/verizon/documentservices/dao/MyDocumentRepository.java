package org.verizon.documentservices.dao;

import java.util.List;
import java.util.Map;

import org.verizon.documentservices.model.Document;

/**
 * This class holds all the possible search scenarios for the document repository
 * 
 * @author Shankar K
 *
 */
public interface MyDocumentRepository {
	
	/**
	 * This method finds the list of documents with given docName
	 * 
	 * @param docName the docName to find
	 * @return List<Document> matches the given criteria
	 */
	public List<Document> findByDocNamePattern(String docName);
	
	/**
	 * This method finds the list of documents with given docType
	 * 
	 * @param docType the docType to find
	 * @return List<Document> matches the given criteria
	 */
	public List<Document> findByDocTypePattern(String docType);
	
	/**
	 * This method finds the list of documents with given user
	 * 
	 * @param user the user to find
	 * @return List<Document> matches the given criteria
	 */
	public List<Document> findDocByUser(String user);
	
	/**
	 * This method finds the list of documents with given group
	 * 
	 * @param group the group to find
	 * @return List<Document> matches the given criteria
	 */
	public List<Document> findDocByGroup(String group);
	
	/**
	 * This method finds the list of documents with given criteria
	 * 
	 * @param category the category to find
	 * @return List<Document> matches the given criteria
	 */
	public List<Document> findDocByCategory(String category);

	/**
	 * This method finds the list of documents with given criteria
	 * 
	 * @param pattern the pattern to extract
	 * @return List<Document> matches the given criteria
	 */
	public List<Document> extractMetaData(String pattern);

	/**
	 * This method finds the list of documents with given criteria
	 * 
	 * @param payload the payload to store
	 * @return List<Document> matches the given criteria
	 */
	public String storeMetaData(Map<String, String> payload);

	/**
	 * This method finds the list of documents with given criteria
	 * 
	 * @param tags the tags to find
	 * @return List<Document> matches the given criteria
	 */
	public List<Document> findDocByTags(String tags);

	/**
	 * This method finds the list of documents with given criteria
	 * 
	 * @param searchPattern teh searchPattern to search
	 * @return List<Document> matches the given criteria
	 */
	public List<Document> findAllDocumentsBySearchPattern(String searchPattern);

	/**
	 * This method finds the list of documents with given criteria
	 * 
	 * @param payload the payload to search
	 * @return List<Document> matches the given criteria
	 */
	public List<Document> findDocumentsByFilteredSearchPattern(Map<String, String> payload);
}
