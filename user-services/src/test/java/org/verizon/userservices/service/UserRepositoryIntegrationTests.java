/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.verizon.userservices.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.verizon.userservices.application.UserServicesApplication;
import org.verizon.userservices.domain.AppUser;
import org.verizon.userservices.domain.AppGroup;

/**
 * Integration tests for {@link UserRepository}.
 *
 * @author 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(UserServicesApplication.class)
public class UserRepositoryIntegrationTests {

	@Autowired
	UserServiceRepository repository;
	@Autowired
	UserWorkGroupServiceRepository workgrouprep;
	
	@Test
	public void executesQueryMethodsCorrectly() {

		//AppUser userName = this.repository.findUserByFirstName("Rao");
		//List<AppUser> users = this.repository.findAll();
		//AppUser userName = this.repository.findUserByUserId(101);
		//this.repository.deleteUserByUserId(102);
		//AppUser userName = this.repository.findByGarmLevel(101);
		
		//AppGroup findWorkGroupById = workgrouprep.findWorkGroupById(1002);
		int findWorkGroupById = 1001;
		//List<AppUser> users = this.repository.findAllUsersByWorkGroup(workgrouprep.getOne(findWorkGroupById));
		AppGroup wkg = workgrouprep.findOne(findWorkGroupById);
		List<AppUser> users = new ArrayList<AppUser>(1); //this.repository.findAllUsersByWorkGroup(wkg);
		//System.out.println("Username::::"+userName + "--------------------" + users);
		
		System.out.println("users::::"+users.size());
		//assertThat(cities.getTotalElements()).isGreaterThan(20L);
	}
}
