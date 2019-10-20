package org.verizon.sharingservices.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.verizon.sharingservices.domain.User;
import org.verizon.sharingservices.model.Email;
import org.verizon.sharingservices.service.EmailService;

/**
 * SpringBootApplication
 * @author Bhuvitha
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = { "org.verizon.sharingservices" })
@EnableJpaRepositories(basePackages = { "org.verizon.sharingservices.service" })
@EntityScan(basePackageClasses = { Email.class })
public class SharingServicesApplication{ //implements CommandLineRunner{

	@Autowired
	private EmailService sharingService;	
	
	/*@Override
	public void run(String... args) throws Exception {
		User toUser = new User();
		toUser.setFirstName("Abhishek");
		toUser.setLastName("TL");
		toUser.setEmailAddress("tad@gmail.com");
		User fromUser = new User();
		toUser.setFirstName("Bhuvitha");
		toUser.setLastName("G");
		toUser.setEmailAddress("bhuvi@gmail.com");
		List<User> toUsers = new ArrayList<User>();
		toUsers.add(toUser);
		System.out.println(" *********calling sharingService.shareWithUsers *********");
		String result = sharingService.shareWithUsers(toUsers, "http://google.com", fromUser);
		System.out.println(" *********result ********* "+ result);
	}*/
	public static void main(String[] args) throws Exception {
		SpringApplication.run(SharingServicesApplication.class, args);
	}

}
