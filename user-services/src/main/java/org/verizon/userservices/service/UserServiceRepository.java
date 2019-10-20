package org.verizon.userservices.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.verizon.userservices.domain.AppUser;
import org.verizon.userservices.domain.AppGroup;

/**
 * This UserServiceRepository class is used to define all the methods related to UserServiceRepository.
 * @author Giri
 *
 */

public interface UserServiceRepository extends JpaRepository<AppUser, Integer>{

	AppUser findUserByFirstName(String fname);

	AppUser findUserByLastName(String fname);

}
