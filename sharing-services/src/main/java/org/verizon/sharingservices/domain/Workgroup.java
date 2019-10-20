package org.verizon.sharingservices.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Workgroup {
	@Id
	@GeneratedValue
	private int wkgrpId;
	private String wkgrpName;
	private String wkgrpDesc;
	
	
	public int getWkgrpId() {
		return wkgrpId;
	}
	public void setWkgrpId(int wkgrpId) {
		this.wkgrpId = wkgrpId;
	}
	public String getWkgrpName() {
		return wkgrpName;
	}
	public void setWkgrpName(String wkgrpName) {
		this.wkgrpName = wkgrpName;
	}
	
	public String getWkgrpDesc() {
		return wkgrpDesc;
	}
	public void setWkgrpDesc(String wkgrpDesc) {
		this.wkgrpDesc = wkgrpDesc;
	}
	@Override
	public String toString() {
		return "Workgroup [wkgrpId=" + wkgrpId + ", wkgrpName=" + wkgrpName + ", wkgrpDesc=" + wkgrpDesc + "]";
	}

}
