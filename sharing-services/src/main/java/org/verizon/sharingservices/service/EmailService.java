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
 * interface to EmailServiceImpl 
 * @author Bhuvitha
 *
 */
public interface EmailService{
	
	/**
	 * abstract method to share document url with user group through email
	 * @param toWorkGroup
	 * @param url
	 * @param fromUser
	 * @return String
	 */
	String shareWithUserGroup(Workgroup toWorkGroup, String url, User fromUser);
	
	/**
	 * abstract method to share document url with users through email
	 * @param toUsers
	 * @param url
	 * @param fromUser
	 * @return String
	 */
	String shareWithUsers(List<User> toUsers, String url, User fromUser);
		
}