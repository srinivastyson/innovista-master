/**
 * 
 */
/**
 * @author Bhuvitha
 *
 */
package org.verizon.sharingservices.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "email")
public class Email{
	// // Persistent Fields
	@Id
	@GeneratedValue
	private long id;
	private String fromAddress;
	private String toAddress;
	private String emailSubject;
	private String emailBody;
	
	// // Accessor and Mutator Methods
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFromAddress() {
		return fromAddress;
	}
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}
	public String getToAddress() {
		return toAddress;
	}
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}
	public String getEmailSubject() {
		return emailSubject;
	}
	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}
	public String getEmailBody() {
		return emailBody;
	}
	public void setEmailBody(String emailBody) {
		this.emailBody = emailBody;
	}
	// String Representation
	@Override
	public String toString() {
		return "Email [id=" + id + ", fromAddress=" + fromAddress + ", toAddress=" + toAddress + ", emailSubject="
				+ emailSubject + ", emailBody=" + emailBody + "]";
	}

}
