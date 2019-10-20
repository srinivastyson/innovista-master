package org.verizon.documentservices.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.verizon.documentservices.dao.DocumentRepository;
import org.verizon.documentservices.dao.MyDocumentRepository;
import org.verizon.documentservices.model.Document;

/**
 * This is the service layer class for all of the document services
 * @author Shankar K
 *
 */
@Service
public class DocumentServiceImpl implements DocumentService {
	@Autowired
	private DocumentRepository repository;

	@Autowired
	private MyDocumentRepository myDocuRepo;

	/* (non-Javadoc)
	 * @see org.verizon.documentservices.services.DocumentService#findByDocuName(java.lang.String)
	 */
	@Override
	public Document findByDocuName(String docName) {
		return repository.findByDocName(docName);
	}

	/* (non-Javadoc)
	 * @see org.verizon.documentservices.services.DocumentService#findByDocuType(java.lang.String)
	 */
	@Override
	public List<Document> findByDocuType(String docType) {
		return (List<Document>) repository.findByDocType(docType);
	}

	/* (non-Javadoc)
	 * @see org.verizon.documentservices.services.DocumentService#findByDocuNamePattern(java.lang.String)
	 */
	@Override
	public List<Document> findByDocuNamePattern(String pattern) {
		return (List<Document>) myDocuRepo.findByDocNamePattern(pattern);
	}

	/* (non-Javadoc)
	 * @see org.verizon.documentservices.services.DocumentService#findByDocuTypePattern(java.lang.String)
	 */
	@Override
	public List<Document> findByDocuTypePattern(String docType) {
		return (List<Document>) myDocuRepo.findByDocTypePattern(docType);
	}

	/* (non-Javadoc)
	 * @see org.verizon.documentservices.services.DocumentService#findDocuByUser(java.lang.String)
	 */
	@Override
	public List<Document> findDocuByUser(String user) {
		return (List<Document>) myDocuRepo.findDocByUser(user);
	}

	/* (non-Javadoc)
	 * @see org.verizon.documentservices.services.DocumentService#findDocuByGroup(java.lang.String)
	 */
	@Override
	public List<Document> findDocuByGroup(String group) {
		return (List<Document>) myDocuRepo.findDocByGroup(group);
	}

	/* (non-Javadoc)
	 * @see org.verizon.documentservices.services.DocumentService#findDocuByCategory(java.lang.String)
	 */
	@Override
	public List<Document> findDocuByCategory(String category) {
		return (List<Document>) myDocuRepo.findDocByCategory(category);
	}

	/* (non-Javadoc)
	 * @see org.verizon.documentservices.services.DocumentService#extractMetaData(java.lang.String)
	 */
	@Override
	public List<Document> extractMetaData(String pattern) {
		return (List<Document>) myDocuRepo.extractMetaData(pattern);
	}

	/* (non-Javadoc)
	 * @see org.verizon.documentservices.services.DocumentService#storeMetaData(java.util.Map)
	 */
	@Override
	public String storeMetaData(Map<String, String> payload) {
		return myDocuRepo.storeMetaData(payload);
	}

	/* (non-Javadoc)
	 * @see org.verizon.documentservices.services.DocumentService#findByDocuTags(java.lang.String)
	 */
	@Override
	public List<Document> findByDocuTags(String tags) {
		return myDocuRepo.findDocByTags(tags);
	}

	/* (non-Javadoc)
	 * @see org.verizon.documentservices.services.DocumentService#findAllDocumentsBySearchPattern(java.lang.String)
	 */
	@Override
	public List<Document> findAllDocumentsBySearchPattern(String searchPattern) {
		return myDocuRepo.findAllDocumentsBySearchPattern(searchPattern);
	}

	/* (non-Javadoc)
	 * @see org.verizon.documentservices.services.DocumentService#findDocumentsByFilteredSearchPattern(java.util.Map)
	 */
	@Override
	public List<Document> findDocumentsByFilteredSearchPattern(Map<String, String> payload) {
		return myDocuRepo.findDocumentsByFilteredSearchPattern(payload);
	}
}
