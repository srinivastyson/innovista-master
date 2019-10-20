package org.verizon.common.dataobjects;

import java.io.File;
import java.io.Serializable;

public class Document implements Serializable {
	private static final long serialVersionUID = -3644239873338105561L;
	private String docId;
	private String name;
	private String mimeType;
	private String type;
	private String modifiedBy;
	private String modifiedOn;
	private transient File physicalFile;

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(String modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public File getPhysicalFile() {
		return physicalFile;
	}

	public void setPhysicalFile(File physicalFile) {
		this.physicalFile = physicalFile;
	}

	@Override
	public String toString() {
		return "Document [docId=" + docId + ", name=" + name + "]";
	}

}
