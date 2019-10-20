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

/**
 * Interface to share document link with users and user group
 * @author Bhuvitha
 *
 */
public interface SalesforceService{
	
	/**
	 * Responsible for sharing document url with a user group of users in sales force
	 * @param toWorkGroup
	 * @param url
	 * @param fromUser
	 * @return String
	 */
	String shareWithUserGroup(Workgroup toWorkGroup, String url, User fromUser);
	
	/**
	 * Responsible for sharing document url with list of users in sales force
	 * @param toUsers
	 * @param url
	 * @param fromUser
	 * @return String
	 */
	String shareWithUsers(List<User> toUsers, String url, User fromUser);
	
}