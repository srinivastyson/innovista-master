package org.verizon.documentservices.model;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;

/**
 * Document - The POJO class
 * 
 * @author Shankar K
 *
 */
@Entity
public class Document {

	@Id
	private String docId;
	private String docName;
	private String docType;
	private String docState;
	private String docTitle;
	private String docDescription;
	private String majorVersion;
	private String minorVersion;
	private String garmLevel;
	private String gsamLevel;
	private String source;
	private String content;
	private String tags;
	private String language;	
	private String user;
	private String category;
	private String group;
	private String userEmail;
	private String mimeType;	
	private String creator;
	private String lastContributor;	
	private String createdAt;	
	private String lastModifiedAt;	
	private String expiresAt;	
	private String size;
	
	public Document() {
	}
	
	/**
	 * 
	 * @param docId
	 * @param docName
	 * @param docType
	 */
	public Document(String docId, String docName, String docType) {
		this.docId = docId;
		this.docName = docName;
		this.docType = docType;
	}	

	/**
	 * @return the docId
	 */
	public String getDocId() {
		return docId;
	}

	/**
	 * @param docId the docId to set
	 */
	public void setDocId(String docId) {
		this.docId = docId;
	}

	/**
	 * @return the docName
	 */
	public String getDocName() {
		return docName;
	}

	/**
	 * @param docName the docName to set
	 */
	public void setDocName(String docName) {
		this.docName = docName;
	}

	/**
	 * @return the docType
	 */
	public String getDocType() {
		return docType;
	}

	/**
	 * @param docType the docType to set
	 */
	public void setDocType(String docType) {
		this.docType = docType;
	}

	/**
	 * @return the docTitle
	 */
	public String getDocTitle() {
		return docTitle;
	}

	/**
	 * @param docTitle the docTitle to set
	 */
	public void setDocTitle(String docTitle) {
		this.docTitle = docTitle;
	}

	/**
	 * @return the docDescription
	 */
	public String getDocDescription() {
		return docDescription;
	}

	/**
	 * @param docDescription the docDescription to set
	 */
	public void setDocDescription(String docDescription) {
		this.docDescription = docDescription;
	}

	/**
	 * @return the majorVersion
	 */
	public String getMajorVersion() {
		return majorVersion;
	}

	/**
	 * @param majorVersion the majorVersion to set
	 */
	public void setMajorVersion(String majorVersion) {
		this.majorVersion = majorVersion;
	}

	/**
	 * @return the minorVersion
	 */
	public String getMinorVersion() {
		return minorVersion;
	}

	/**
	 * @param minorVersion the minorVersion to set
	 */
	public void setMinorVersion(String minorVersion) {
		this.minorVersion = minorVersion;
	}

	/**
	 * @return the garmLevel
	 */
	public String getGarmLevel() {
		return garmLevel;
	}

	/**
	 * @param garmLevel the garmLevel to set
	 */
	public void setGarmLevel(String garmLevel) {
		this.garmLevel = garmLevel;
	}

	/**
	 * @return the gsamLevel
	 */
	public String getGsamLevel() {
		return gsamLevel;
	}

	/**
	 * @param gsamLevel the gsamLevel to set
	 */
	public void setGsamLevel(String gsamLevel) {
		this.gsamLevel = gsamLevel;
	}

	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the tags
	 */
	public String getTags() {
		return tags;
	}

	/**
	 * @param tags the tags to set
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}

	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the group
	 */
	public String getGroup() {
		return group;
	}

	/**
	 * @param group the group to set
	 */
	public void setGroup(String group) {
		this.group = group;
	}

	/**
	 * @return the userEmail
	 */
	public String getUserEmail() {
		return userEmail;
	}

	/**
	 * @param userEmail the userEmail to set
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	/**
	 * @return the mimeType
	 */
	public String getMimeType() {
		return mimeType;
	}

	/**
	 * @param mimeType the mimeType to set
	 */
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	/**
	 * @return the creator
	 */
	public String getCreator() {
		return creator;
	}

	/**
	 * @param creator the creator to set
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}

	/**
	 * @return the lastContributor
	 */
	public String getLastContributor() {
		return lastContributor;
	}

	/**
	 * @param lastContributor the lastContributor to set
	 */
	public void setLastContributor(String lastContributor) {
		this.lastContributor = lastContributor;
	}

	/**
	 * @return the createdAt
	 */
	public String getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @return the lastModifiedAt
	 */
	public String getLastModifiedAt() {
		return lastModifiedAt;
	}

	/**
	 * @param lastModifiedAt the lastModifiedAt to set
	 */
	public void setLastModifiedAt(String lastModifiedAt) {
		this.lastModifiedAt = lastModifiedAt;
	}

	/**
	 * @return the expiresAt
	 */
	public String getExpiresAt() {
		return expiresAt;
	}

	/**
	 * @param expiresAt the expiresAt to set
	 */
	public void setExpiresAt(String expiresAt) {
		this.expiresAt = expiresAt;
	}

	/**
	 * @return the docState
	 */
	public String getDocState() {
		return docState;
	}

	/**
	 * @param docState the docState to set
	 */
	public void setDocState(String docState) {
		this.docState = docState;
	}

	/**
	 * @return the size
	 */
	public String getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(String size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return String.format("Document[docId=%s, docName='%s', docType='%s']", docId, docName, docType);
	}

}
