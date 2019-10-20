package org.verizon.userservices.service;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.verizon.userservices.domain.AppUser;
import org.verizon.userservices.domain.AppGroup;
/**
 * This UserServiceImpl class is used to implement all the methods related to AppUser available in UserService
 * @author Giri
 *
 */

@Component("userService")
@Transactional
public class UserServiceImpl implements UserService {
	Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private final UserServiceRepository userRepository;
	
	private final UserWorkGroupServiceRepository workgrouprep;
	
	@Autowired
	public UserServiceImpl(UserServiceRepository userRepository, UserWorkGroupServiceRepository workgrouprep) {
		this.userRepository = userRepository;
		this.workgrouprep = workgrouprep;
	}
	
	@Override
	public AppUser getUserById(int userId) {
		log.debug("getUserById() - Entering ");
		Assert.notNull(userId, "userId must not be null");
		log.debug("getUserById() - Exiting ");
		return this.userRepository.findOne(userId);
	}

	@Override
	public AppUser getUserByName(String name) {
		log.debug("getUserByName() - Entering ");
		Assert.hasLength(name, "Name must not be empty");
		log.debug("getUserByName() - Exiting ");
		return this.userRepository.findUserByFirstName(name);
	}
	
	@Override
	public List<AppUser> getAllUsers(int wrkgrpId) {
		log.debug("getAllUsers() - Entering ");
		AppGroup wkg = workgrouprep.findOne(wrkgrpId);
		log.debug("getAllUsers() - Exiting ");
		//return this.userRepository.findAllUsersByWorkGroup(wkg);
		return null;
	}
	
	@Override
	public AppUser getUserByGarmLevel(int userId) {
		log.debug("getUserByGarmLevel() - Entering ");
		Assert.notNull(userId, "userId must not be null");
		log.debug("getUserByGarmLevel() - Exiting ");
		//return this.userRepository.findByGarmLevel(userId);
		return null;
	}
	
	@Override
	public void deleteUser(int userId) {
		log.debug("deleteUser() - Entering ");
		userRepository.delete(userId);
		log.debug("deleteUser() - Exiting ");
	}
	
	@Override
	public void registerUser(AppUser user) {
		log.debug("registerUser() - Entering ");
		userRepository.save(user);
		log.debug("registerUser() - Exiting ");
	}
}
