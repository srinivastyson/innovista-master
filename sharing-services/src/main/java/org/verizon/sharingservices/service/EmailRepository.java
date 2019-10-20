package org.verizon.sharingservices.service;

import org.springframework.data.repository.Repository;
import org.verizon.sharingservices.model.Email;

/**
 * Repository to save Queued emails.
 * @author Bhuvitha
 *
 */
public interface EmailRepository extends Repository<Email, Long> {

	/**
	 * saves queued email to DB
	 * @param email
	 * @return Email
	 */
	Email save(Email email);
}
