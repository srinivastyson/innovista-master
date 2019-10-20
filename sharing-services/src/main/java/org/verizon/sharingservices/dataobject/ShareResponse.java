package org.verizon.sharingservices.dataobject;

import java.util.List;

import org.verizon.sharingservices.domain.User;
import org.verizon.sharingservices.domain.Workgroup;

/**
 * Response Data transfer object
 * @author Bhuvitha
 *
 */
public class ShareResponse {
	private String responseType = "";
	private String responseText = "";
	
	public String getResponseType() {
		return responseType;
	}
	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}
	public String getResponseText() {
		return responseText;
	}
	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}

}
