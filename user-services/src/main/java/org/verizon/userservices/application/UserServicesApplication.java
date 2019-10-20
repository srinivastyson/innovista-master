package org.verizon.userservices.application;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.verizon.userservices.domain.AppUser;
import org.verizon.userservices.service.UserServiceRepository;

@SpringBootApplication
@ComponentScan(basePackages = {
    "org.verizon.userservices.service"
})
@EnableJpaRepositories(basePackages = {
    "org.verizon.userservices.service"
})
@EntityScan(basePackages = {
    "org.verizon.userservices.domain"
})
public class UserServicesApplication {
    @Bean
    public CommandLineRunner demo(final UserServiceRepository repository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                List<AppUser> users = repository.findAll();
                for (AppUser userFound : users) {
                    System.out.println(userFound);
                }
            }
        };
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(UserServicesApplication.class, args);
    }
}
