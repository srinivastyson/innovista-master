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
 * Implementation class for sharing document url through email with users and user group
 * @author Bhuvitha
 *
 */
@Component("sharingService")
@Transactional
public class EmailServiceImpl implements EmailService {
	
	private Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

	private EmailRepository emailRepository;
	
	@Autowired
	public EmailServiceImpl(EmailRepository emailRepository){
		this.emailRepository = emailRepository;
	}

	/* (non-Javadoc)
	 * @see org.verizon.sharingservices.service.EmailService#shareWithUserGroup(org.verizon.sharingservices.domain.Workgroup, java.lang.String, org.verizon.sharingservices.domain.User)
	 */
	public String shareWithUserGroup(Workgroup toWorkGroup, String url, User fromUser) {
		logger.info("In side EmailServiceImpl, shareWithUserGroup ...");
		List<User> toUsers = new ArrayList(); //userServices.getUsersFromWorkgroups(toWorkGroups);
		return shareWithUsers(toUsers, url, fromUser);
	}

	/* (non-Javadoc)
	 * @see org.verizon.sharingservices.service.EmailService#shareWithUsers(java.util.List, java.lang.String, org.verizon.sharingservices.domain.User)
	 */
	public String shareWithUsers(List<User> toUsers , String url, User fromUser) {
		logger.info("In side EmailServiceImpl, shareWithUsers ...");
		Email email = new Email();
		
		if(toUsers != null && toUsers.size() > 0){
			StringBuffer toList = new StringBuffer();
			for(User user : toUsers){
				toList.append(user.getEmailAddress()).append(";");
			}
			email.setToAddress(toList.toString());
		}
		else{
			logger.error("Exiting, Missing To List ...");
			return "Missing To List";
		}
		
		if(fromUser != null && url != null && !url.isEmpty()){
			email.setFromAddress(fromUser.getEmailAddress());	
			email.setEmailSubject(fromUser.getFirstName() + " has shared a file with you.");
			
			StringBuffer emailBody = new StringBuffer("Hi,")
					.append("\n").append("You can access the file through below url ..")
					.append("<h>" + url + "</h>").append("\n").append("\n")
					.append("Thanks and Regards,").append("\n")
					.append(fromUser.getFirstName()).append(" ").append(fromUser.getLastName());
			
			email.setEmailBody(emailBody.toString());
		}
		else{
			logger.error("Missing URL or from field ...");
			return "Missing URL or from field";
		}
		GmailSMTPEmailSender emailSender = new GmailSMTPEmailSender(email);
		//Sending email and processing the state of email sent
		switch(emailSender.execute()){
		case SUCCESS:
			logger.info("Exiting, Email sent Successfully ...");
			return "Email sent Successfully";
		case FAILURE:
			logger.info("Exiting, Not able to send the Email ...");
			return "Not able to send the Email";
		case QUEUED:
			email = emailRepository.save(email);
			if(email != null){
				logger.info("Exiting,Email Queued ..." +email);
				return "Email Queued";
			}
			else {
				logger.error("Exiting, Not able to Queue the email ...");
				return "Not able to Queue the email";
			}
		default:
			break;
		}
		return "Sucess";
	}

}
