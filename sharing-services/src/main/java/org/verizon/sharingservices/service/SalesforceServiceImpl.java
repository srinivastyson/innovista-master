package org.verizon.sharingservices.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.verizon.sharingservices.domain.User;
import org.verizon.sharingservices.domain.Workgroup;
import org.verizon.sharingservices.model.Email;
import org.verizon.sharingservices.util.GmailSMTPEmailSender;

/**
 * Implementation class for sharing document url with users and user group in sales force
 * @author Bhuvitha
 *
 */
@Component("salesforceService")
@Transactional
public class SalesforceServiceImpl implements SalesforceService {
	
	private Logger logger = LoggerFactory.getLogger(SalesforceServiceImpl.class);

	
	/* (non-Javadoc)
	 * @see org.verizon.sharingservices.service.SalesforceService#shareWithUserGroup(org.verizon.sharingservices.domain.Workgroup, java.lang.String, org.verizon.sharingservices.domain.User)
	 */
	public String shareWithUserGroup(Workgroup toWorkGroup, String url, User fromUser) {
		logger.info("In side shareWithUserGroup ...");
		List<User> toUsers = new ArrayList(); //userServices.getUsersFromWorkgroups(toWorkGroups);
		return shareWithUsers(toUsers, url, fromUser);
	}

	/* (non-Javadoc)
	 * @see org.verizon.sharingservices.service.SalesforceService#shareWithUsers(java.util.List, java.lang.String, org.verizon.sharingservices.domain.User)
	 */
	public String shareWithUsers(List<User> toUsers , String url, User fromUser) {
		logger.info("In side shareWithUsers ...");
		return "Corresponsing API Needs to be Implemented";
	}
}
