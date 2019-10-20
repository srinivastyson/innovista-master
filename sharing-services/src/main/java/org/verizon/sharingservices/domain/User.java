package org.verizon.sharingservices.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class User {
	@Id
	@GeneratedValue
	private int userId;
	private String vzId;
	private String firstName;
	private String lastName;
	private int garmLevel;
	private int workGroup;
	private String emailAddress;
	
	public int getUserId() {
		return userId;	
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getVzId() {
		return vzId;
	}
	public void setVzId(String vzId) {
		this.vzId = vzId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getGarmLevel() {
		return garmLevel;
	}
	public void setGarmLevel(int garmLevel) {
		this.garmLevel = garmLevel;
	}
	public int getWorkGroup() {
		return workGroup;
	}
	public void setWorkGroup(int workGroup) {
		this.workGroup = workGroup;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", vzId=" + vzId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", garmLevel=" + garmLevel + ", workGroup=" + workGroup + ", emailAddress=" + emailAddress + "]";
	}
}

