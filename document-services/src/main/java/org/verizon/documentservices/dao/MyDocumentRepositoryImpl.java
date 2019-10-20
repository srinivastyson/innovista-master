package org.verizon.documentservices.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.verizon.documentservices.model.Document;

/**
 * The implementation class of MyDocumentRepository
 * 
 * @author Shankar K
 *
 */
@Repository
public class MyDocumentRepositoryImpl implements MyDocumentRepository {

	@Autowired
	private MongoTemplate mongoTemplate;
	@Autowired
	private DocumentRepository repository;
	
	/* (non-Javadoc)
	 * @see org.verizon.documentservices.dao.MyDocumentRepository#findByDocNamePattern(java.lang.String)
	 */
	@Override
	public List<Document> findByDocNamePattern(String docName) {
		Criteria criteria = Criteria.where("docName").regex(docName);
		return mongoTemplate.find(Query.query(criteria), Document.class);
	}

	/* (non-Javadoc)
	 * @see org.verizon.documentservices.dao.MyDocumentRepository#findByDocTypePattern(java.lang.String)
	 */
	@Override
	public List<Document> findByDocTypePattern(String docType) {
		Criteria criteria = Criteria.where("docType").regex(docType);
		return mongoTemplate.find(Query.query(criteria), Document.class);
	}

	/* (non-Javadoc)
	 * @see org.verizon.documentservices.dao.MyDocumentRepository#findDocByUser(java.lang.String)
	 */
	@Override
	public List<Document> findDocByUser(String user) {
		Criteria criteria = Criteria.where("user").regex(user);
		return mongoTemplate.find(Query.query(criteria), Document.class);
	}

	/* (non-Javadoc)
	 * @see org.verizon.documentservices.dao.MyDocumentRepository#findDocByGroup(java.lang.String)
	 */
	@Override
	public List<Document> findDocByGroup(String group) {
		Criteria criteria = Criteria.where("group").regex(group);
		return mongoTemplate.find(Query.query(criteria), Document.class);
	}

	/* (non-Javadoc)
	 * @see org.verizon.documentservices.dao.MyDocumentRepository#findDocByCategory(java.lang.String)
	 */
	@Override
	public List<Document> findDocByCategory(String category) {
		Criteria criteria = Criteria.where("category").regex(category);
		return mongoTemplate.find(Query.query(criteria), Document.class);
	}

	/* (non-Javadoc)
	 * @see org.verizon.documentservices.dao.MyDocumentRepository#extractMetaData(java.lang.String)
	 */
	@Override
	public List<Document> extractMetaData(String pattern) {
		Criteria category = Criteria.where("category").regex(pattern);
		Criteria docName = Criteria.where("docName").regex(pattern);
		Criteria docType = Criteria.where("docType").regex(pattern);
		Criteria user = Criteria.where("user").regex(pattern);
		Criteria group = Criteria.where("group").regex(pattern);
		Criteria docId = Criteria.where("docId").regex(pattern);
		Criteria docTitle = Criteria.where("docTitle").regex(pattern);
		Criteria source = Criteria.where("source").regex(pattern);
		Criteria tags = Criteria.where("tags").regex(pattern);
		Criteria userEmail = Criteria.where("userEmail").regex(pattern);
		Criteria content = Criteria.where("content").regex(pattern);
		
		List<Criteria> criterias = new ArrayList<Criteria>();
		criterias.add(category);
		criterias.add(docName);
		criterias.add(docType);
		criterias.add(user);
		criterias.add(group);
		criterias.add(docId);
		criterias.add(docTitle);
		criterias.add(source);
		criterias.add(tags);
		criterias.add(userEmail);
		criterias.add(content);
		
		Criteria criteria = new Criteria().orOperator(criterias.toArray(new Criteria[criterias.size()]));
		
		return mongoTemplate.find(Query.query(criteria), Document.class);
	}

	/* (non-Javadoc)
	 * @see org.verizon.documentservices.dao.MyDocumentRepository#storeMetaData(java.util.Map)
	 */
	@Override
	public String storeMetaData(Map<String, String> payload) {
//		Iterator<Map.Entry<String, String>> iterator = payload.entrySet().iterator() ;
//        while(iterator.hasNext()){
//            Map.Entry<String, String> docuEntry = iterator.next();
//            System.out.println(docuEntry.getKey() +" :: "+ docuEntry.getValue());
//        }
            
        Document docu = new Document();
        docu.setDocId(payload.get("docId"));
        docu.setDocName(payload.get("docName"));
		docu.setDocType(payload.get("docType"));
        docu.setDocState(payload.get("docState"));
        docu.setDocTitle(payload.get("docTitle"));
        docu.setDocDescription(payload.get("docDescription"));
        docu.setMajorVersion(payload.get("majorVersion"));
        docu.setMinorVersion(payload.get("minorVersion"));
        docu.setGarmLevel(payload.get("garmLevel"));
        docu.setGsamLevel(payload.get("gsamLevel"));
        docu.setSource(payload.get("source"));
        docu.setContent(payload.get("content"));
        docu.setLanguage(payload.get("language"));
        docu.setUser(payload.get("user"));
        docu.setCategory(payload.get("category"));
        docu.setGroup(payload.get("group"));
        docu.setUserEmail(payload.get("userEmail"));
        docu.setMimeType(payload.get("mimeType"));
        docu.setCreator(payload.get("creator"));
        docu.setLastContributor(payload.get("lastContributor"));
        docu.setCreatedAt(payload.get("createdAt"));
        docu.setLastModifiedAt(payload.get("lastModifiedAt"));
        docu.setExpiresAt(payload.get("expiresAt"));
        
        Document d = repository.save(docu);
        if (d != null)
        	return "SUCCESS - " + d.getDocId();
        else
        	return "FAILURE - " + docu.getDocId();
            
     }

	/* (non-Javadoc)
	 * @see org.verizon.documentservices.dao.MyDocumentRepository#findDocByTags(java.lang.String)
	 */
	@Override
	public List<Document> findDocByTags(String tags) {
		Criteria criteria = Criteria.where("tags").regex(tags);
		return mongoTemplate.find(Query.query(criteria), Document.class);
	}

	/* (non-Javadoc)
	 * @see org.verizon.documentservices.dao.MyDocumentRepository#findAllDocumentsBySearchPattern(java.lang.String)
	 */
	@Override
	public List<Document> findAllDocumentsBySearchPattern(String searchPattern) {
		Criteria category = Criteria.where("category").regex(searchPattern);
		Criteria docName = Criteria.where("docName").regex(searchPattern);
		Criteria docType = Criteria.where("docType").regex(searchPattern);
		Criteria user = Criteria.where("user").regex(searchPattern);
		Criteria group = Criteria.where("group").regex(searchPattern);
		Criteria docId = Criteria.where("docId").regex(searchPattern);
		Criteria docTitle = Criteria.where("docTitle").regex(searchPattern);
		Criteria source = Criteria.where("source").regex(searchPattern);
		Criteria tags = Criteria.where("tags").regex(searchPattern);
		Criteria userEmail = Criteria.where("userEmail").regex(searchPattern);
		Criteria content = Criteria.where("content").regex(searchPattern);
		
		List<Criteria> criterias = new ArrayList<Criteria>();
		criterias.add(category);
		criterias.add(docName);
		criterias.add(docType);
		criterias.add(user);
		criterias.add(group);
		criterias.add(docId);
		criterias.add(docTitle);
		criterias.add(source);
		criterias.add(tags);
		criterias.add(userEmail);
		criterias.add(content);
		
		Criteria criteria = new Criteria().orOperator(criterias.toArray(new Criteria[criterias.size()]));
		
		return mongoTemplate.find(Query.query(criteria), Document.class);
	}

	/* (non-Javadoc)
	 * @see org.verizon.documentservices.dao.MyDocumentRepository#findDocumentsByFilteredSearchPattern(java.util.Map)
	 */
	@Override
	public List<Document> findDocumentsByFilteredSearchPattern(Map<String, String> payload) {
		
		String searchPattern  = payload.get("searchString");
		String filterTypes = payload.get("filterTypes");
		List<Criteria> criterias = new ArrayList<Criteria>();		
		
		if(filterTypes != null && !filterTypes.isEmpty()){
			String filterType []= filterTypes.split("#");
			for(int i=0; i<filterType.length;i++) {
				Criteria newCriteria = Criteria.where(filterType[i]).regex(searchPattern);
				criterias.add(newCriteria);
			}
		}
		
		Criteria finalCriteria = new Criteria().orOperator(criterias.toArray(new Criteria[criterias.size()]));
		
		return mongoTemplate.find(Query.query(finalCriteria), Document.class);		
	}
}
