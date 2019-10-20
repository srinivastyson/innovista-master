package org.verizon.sharingservices.dataobject;

import java.util.List;

import org.verizon.sharingservices.domain.User;
import org.verizon.sharingservices.domain.Workgroup;

/**
 * Request Data transfer object  
 * @author Administrator
 *
 */
public class ShareRequest {
	private Workgroup sendToWorkGroup;
	private List<User> sendTo;
	private String docId;
	private User sentFrom;

	public List<User> getSendTo() {
		return sendTo;
	}

	public void setSendTo(List<User> sendTo) {
		this.sendTo = sendTo;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public User getSentFrom() {
		return sentFrom;
	}

	public void setSentFrom(User sentFrom) {
		this.sentFrom = sentFrom;
	}

	public Workgroup getSendToWorkGroup() {
		return sendToWorkGroup;
	}

	public void setSendToWorkGroup(Workgroup sendToWorkGroup) {
		this.sendToWorkGroup = sendToWorkGroup;
	}

	@Override
	public String toString() {
		return "ShareRequest [docId=" + docId + ", sentFrom=" + sentFrom + "]";
	}

}
