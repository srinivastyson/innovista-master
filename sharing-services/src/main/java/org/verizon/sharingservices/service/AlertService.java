/**
 * 
 */
/**
 * @author bhuvitha
 *
 */
package org.verizon.sharingservices.service;

import java.util.List;
import org.verizon.sharingservices.domain.*;
import org.verizon.sharingservices.model.Alert;

/**
 * Interface to save and retrieve alerts on sharing a document
 * @author Bhuvitha
 *
 */
public interface AlertService{
	
	/**
	 * Abstract method to save the alerts for all the users in given work group with which doc is shared with.
	 * @param toWorkGroup
	 * @param docId
	 * @param fromUser
	 * @return
	 */
	String shareWithUserGroup(Workgroup toWorkGroup, String docId, User fromUser);
	
	/**
	 * Abstract method to save the alerts for all the users, with whom doc is shared.
	 * @param toUsers
	 * @param docId
	 * @param fromUser
	 * @return
	 */
	String shareWithUsers(List<User> toUsers, String docId, User fromUser);
	
	/**
	 * Abstract method to fetch all the new alerts saved against a particular user
	 * @param userId
	 * @return List
	 */
	List<Alert> retrieveAlertsForUser(long userId);
	
}