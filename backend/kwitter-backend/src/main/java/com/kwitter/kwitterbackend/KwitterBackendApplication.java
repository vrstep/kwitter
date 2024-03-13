package com.kwitter.kwitterbackend;

import com.kwitter.kwitterbackend.models.ApplicationUser;
import com.kwitter.kwitterbackend.models.Role;
import com.kwitter.kwitterbackend.repositories.RoleRepository;
import com.kwitter.kwitterbackend.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;

@SpringBootApplication
public class KwitterBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(KwitterBackendApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepo, UserService userService) {
		return args -> {
			roleRepo.save(new Role(1, "USER"));
//			ApplicationUser u = new ApplicationUser();
//			u.setFirstName("John");
//			u.setLastName("Doe");
//			userService.registerUser(u);
		};
	};
}
