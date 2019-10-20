package org.verizon.documentservices.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.verizon.documentservices.model.Document;

/**
 * This class holds all the basic search scenarios for the document repository
 * 
 * @author Shankar K
 *
 */
public interface DocumentRepository extends MongoRepository<Document, String> {

	/**
	 * This method finds the document with given docId
	 * 
	 * @param docId the docId to find
	 * @return Document matches with docId given
	 */
	public Document findByDocId(String docId);
	
	/**
	 * This method finds the document with given docName
	 * 
	 * @param docName the docName to find
	 * @return Document matches with the docName given
	 */
	public Document findByDocName(String docName);

	/**
	 * This method finds the list of document with given docType
	 * 
	 * @param docType the docType to find
	 * @return List<Document> matches with the docType given
	 */
	public List<Document> findByDocType(String docType);

}
