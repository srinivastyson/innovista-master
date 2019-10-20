package org.verizon.sharingservices.service;

import java.util.List;

import org.springframework.data.repository.Repository;
import org.verizon.sharingservices.model.Alert;

/**
 * @author Bhuvitha
 *
 */
public interface AlertRepository extends Repository<Alert, Long> {

	/**
	 * saves alert to data base
	 * @param alert
	 * @return Alert
	 */
	Alert save(Alert alert);
	
	/**
	 * fetches alerts against user
	 * @param userId
	 * @return List
	 */
	List<Alert> findByUserId(long userId);
	
	/**
	 * deletes alert by user
	 * @param userId
	 */
	void deleteAlertByUserId(long userId);
		
}
