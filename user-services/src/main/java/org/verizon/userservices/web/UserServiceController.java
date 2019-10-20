package org.verizon.userservices.web;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.verizon.userservices.domain.AppUser;
import org.verizon.userservices.service.UserService;

@Controller
public class UserServiceController {

	@Autowired
	private UserService userService;

	@RequestMapping("/")
	@ResponseBody
	@Transactional
	public void registerUser() {
		AppUser user = new AppUser();
		userService.registerUser(user);
	}
}
