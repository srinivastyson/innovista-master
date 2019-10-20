/**
 * 
 */
/**
 * @author Administrator
 *
 */
package org.verizon.sharingservices.web;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.verizon.sharingservices.dataobject.ShareRequest;
import org.verizon.sharingservices.dataobject.ShareResponse;
import org.verizon.sharingservices.model.Alert;
import org.verizon.sharingservices.service.AlertService;
import org.verizon.sharingservices.service.EmailService;
import org.verizon.sharingservices.service.SalesforceService;

/**
 * Controller to route the requests coming in, to corresponding service call
 * @author Bhuvitha
 *
 */
@RestController
public class SharingServiceController {

	/**
	 * services binded
	 */
	@Autowired
	private EmailService emailService;
	@Autowired
	private SalesforceService salesforceService;
	@Autowired
	private AlertService alertService;
	
	/**
	 * This method returns url to access the document given with a document id
	 * @param docId
	 * @return String
	 */
	private String constructUrl(String docId){
		return new StringBuffer("https://www.someurl.com?docid="+docId).toString();
	}
	
	/**
	 * Responsible for sharing the document url with the given list of users through email
	 * @param request
	 * @return ShareResponse
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/shareWithUsersViaEmail")
	@Transactional
	public ShareResponse shareWithUsersViaEmail(@RequestBody ShareRequest request) {
		String resText = emailService.shareWithUsers(request.getSendTo(), constructUrl(request.getDocId()),
				request.getSentFrom());
		ShareResponse response = new ShareResponse();
		response.setResponseText(resText);
		if (!resText.isEmpty()) {
			if (resText.contains("Success")||resText.contains("Email Queued"))
				response.setResponseType("Success");
			else if(resText.contains("Missing"))
				response.setResponseType("Warning");
			else
				response.setResponseType("Failure");
		}
		return response;
	}

	/**
	 * Responsible for for sharing the document url with all the users of given work group through email
	 * @param request
	 * @return ShareResponse
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/shareWithUserGroupViaEmail")
	@Transactional
	public ShareResponse shareWithUserGroupViaEmail(@RequestBody ShareRequest request) {
		String resText = emailService.shareWithUserGroup(request.getSendToWorkGroup(), constructUrl(request.getDocId()),
				request.getSentFrom());
		ShareResponse response = new ShareResponse();
		response.setResponseText(resText);
		if (!resText.isEmpty()) {
			if (resText.contains("Success"))
				response.setResponseType("Success");
			else if(resText.contains("Missing"))
				response.setResponseType("Warning");
			else if(resText.contains("Not able to"))
				response.setResponseType("Failure");
		}
		return response;
	}
	
	/**
	 * Responsible for saving the alerts pertaining to users
	 * @param request
	 * @return ShareResponse
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/shareWithUsersWithInApplication")
	@Transactional
	public ShareResponse shareWithUsersWithInApplication(@RequestBody ShareRequest request) {
		String resText = alertService.shareWithUsers(request.getSendTo(), request.getDocId(),
				request.getSentFrom());
		ShareResponse response = new ShareResponse();
		response.setResponseText(resText);
		if (!resText.isEmpty()) {
			if (resText.contains("Success"))
				response.setResponseType("Success");
			else if(resText.contains("Missing"))
				response.setResponseType("Warning");
			else
				response.setResponseType("Failure");
		}
		return response;
	}
	
	/**
	 * Responsible for saving the alerts pertaining to a user group
	 * @param request
	 * @return ShareResponse
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/shareWithUserGroupWithInApplication")
	@Transactional
	public ShareResponse shareWithUserGroupWithInApplication(@RequestBody ShareRequest request) {
		String resText = alertService.shareWithUserGroup(request.getSendToWorkGroup(), request.getDocId(),
				request.getSentFrom());
		ShareResponse response = new ShareResponse();
		response.setResponseText(resText);
		if (!resText.isEmpty()) {
			if (resText.contains("Success"))
				response.setResponseType("Success");
			else if(resText.contains("Missing"))
				response.setResponseType("Warning");
			else if(resText.contains("Not able to"))
				response.setResponseType("Failure");
		}
		return response;
	}
	
	/**
	 * Retrieves all the new alerts saved for users, logically on user login
	 * @param userId
	 * @return List
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/readAlertsSharedWithUser/{userId}")
	@Transactional
	public List<Alert> readAlertsSharedWithUser(@PathVariable("userId") long userId) {
		List<Alert> alerts = alertService.retrieveAlertsForUser(userId);
		return alerts;
	}
	
	/**
	 * For sharing document url with list of users in sales force
	 * @param request
	 * @return ShareResponse
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/shareWithUsersInSalesForce")
	@Transactional
	public ShareResponse shareWithUsersInsalesForce(@RequestBody ShareRequest request) {
		String resText = salesforceService.shareWithUsers(request.getSendTo(), constructUrl(request.getDocId()),
				request.getSentFrom());
		ShareResponse response = new ShareResponse();
		response.setResponseText(resText);
		response.setResponseType("Warning");
		return response;
	}
	
	/**
	 * For sharing document url with a user group in sales force
	 * @param request
	 * @return ShareResponse
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/shareWithUserGroupsInSalesForce")
	@Transactional
	public ShareResponse shareWithUserGroupInsalesForce(@RequestBody ShareRequest request) {
		String resText = salesforceService.shareWithUserGroup(request.getSendToWorkGroup(), constructUrl(request.getDocId()),
				request.getSentFrom());
		ShareResponse response = new ShareResponse();
		response.setResponseText(resText);
		response.setResponseType("Warning");
		return response;
	}
}