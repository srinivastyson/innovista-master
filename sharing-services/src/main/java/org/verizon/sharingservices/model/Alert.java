package org.verizon.sharingservices.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "alert")
public class Alert {
	// Persistent Fields
	@Id
	@GeneratedValue
	private long alertId;
	private String docId;
	private long userId;
	private long workGroupId;
	private long sharedByWorkGroup;
	private long sharedByUser;
	private Date dateShared;
	
	// Accessor and Mutator Methods
	public long getAlertId() {
		return alertId;
	}
	public void setAlertId(long alertId) {
		this.alertId = alertId;
	}
	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getWorkGroupId() {
		return workGroupId;
	}
	public void setWorkGroupId(long workGroupId) {
		this.workGroupId = workGroupId;
	}
	public long getSharedByWorkGroup() {
		return sharedByWorkGroup;
	}
	public void setSharedByWorkGroup(long sharedByWorkGroup) {
		this.sharedByWorkGroup = sharedByWorkGroup;
	}
	public long getSharedByUser() {
		return sharedByUser;
	}
	public void setSharedByUser(long sharedByUser) {
		this.sharedByUser = sharedByUser;
	}
	public Date getDateShared() {
		return dateShared;
	}
	public void setDateShared(Date dateShared) {
		this.dateShared = dateShared;
	}
	// String Representation
	@Override
	public String toString() {
		return "Alert [alertId=" + alertId + ", docId=" + docId + ", userId=" + userId + ", workGroupId=" + workGroupId
				+ ", sharedByWorkGroup=" + sharedByWorkGroup + ", sharedByUser=" + sharedByUser + ", dateShared="
				+ dateShared + "]";
	}

}
