package org.verizon.sharingservices.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.verizon.sharingservices.domain.User;
import org.verizon.sharingservices.domain.Workgroup;
import org.verizon.sharingservices.model.Alert;

/**
 *  This is facilitates to save and retriev the alerts on what document has been shared with whom and who shared
 * @author Bhuvitha
 *
 */
@Component("alertService")
@Transactional
public class AlertServiceImpl implements AlertService {

	private Logger logger = LoggerFactory.getLogger(AlertServiceImpl.class);
	
	/**  alertRepository to invoked for DB operations */
	private AlertRepository alertRepository;

	/** Constructor of this class 
	 * @param AlertRepository
	 * */
	@Autowired
	public AlertServiceImpl(AlertRepository alertRepository) {
		this.alertRepository = alertRepository;
	}

	/* (non-Javadoc)
	 * @see org.verizon.sharingservices.service.AlertService#shareWithUserGroup(org.verizon.sharingservices.domain.Workgroup, java.lang.String, org.verizon.sharingservices.domain.User)
	 */
	public String shareWithUserGroup(Workgroup toWorkGroup, String docId, User fromUser) {
		logger.info("AlertService, In side shareWithUserGroup ...");
		if (toWorkGroup != null) {
			List<User> toUsers = new ArrayList(); // userServices.getUsersFromWorkgroups(toWorkGroups);
			return shareWithUsers(toUsers, docId, fromUser);
		} else
			return "Missing To Work Group";
	}

	/* (non-Javadoc)
	 * @see org.verizon.sharingservices.service.AlertService#shareWithUsers(java.util.List, java.lang.String, org.verizon.sharingservices.domain.User)
	 */
	public String shareWithUsers(List<User> toUsers, String docId, User fromUser) {
		logger.info("AlertServiceImpl, In side shareWithUsers ...");
		if (fromUser != null) {
			Alert alert;
			if (toUsers != null && toUsers.size() > 0) {
				for (User user : toUsers) {
					alert = new Alert();
					alert.setDocId(docId);
					alert.setSharedByUser(fromUser.getUserId());
					alert.setSharedByWorkGroup(fromUser.getWorkGroup());
					alert.setUserId(user.getUserId());
					alert.setWorkGroupId(user.getWorkGroup());
					alert.setDateShared(new Date());
					alert = alertRepository.save(alert);
					logger.info("AlertServiceImpl, Saved alert ..." + alert);
				}
			} else {
				// If not shared with anyone explicitly, By default it's
				// accessable to Home work group
				// calling user-services to get all Users of WorkGroup
				List<User> workGroupUsers = new ArrayList(); // userServices.getUsersFromWorkgroups(fromUser.getWorkGroup());
				if (workGroupUsers != null) {
					return shareWithUsers(workGroupUsers, docId, fromUser);
				} else {
					logger.error("Missing Work Group and User mapping from user-services...");
					return "Missing Work Group and User mapping from user-services";
				}
			}
			logger.info("success exiting from shareWithUsers ...");
			return "Success";
		} else {
			logger.error("Missing From User, exiting from shareWithUsers ...");
			return "Missing From User";
		}

	}

	/* (non-Javadoc)
	 * @see org.verizon.sharingservices.service.AlertService#retrieveAlertsForUser(long)
	 */
	@Override
	public List<Alert> retrieveAlertsForUser(long userId) {
		List<Alert> alerts = new ArrayList<Alert>();
		logger.info("AlertServiceImpl, In side retrieveAlertsForUser ...");
		alerts = alertRepository.findByUserId(userId);

		// delete the retrieved alerts
		alertRepository.deleteAlertByUserId(userId);
		logger.info("deleting alerts after retrieval, exiting from retrieveAlertsForUser ..." + alerts);
		return alerts;
	}

}
