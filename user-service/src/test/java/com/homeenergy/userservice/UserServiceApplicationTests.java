package com.homeenergy.userservice;

import com.homeenergy.userservice.entity.User;
import com.homeenergy.userservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class UserServiceApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	void contextLoads() {
	}

	@Test
	@Disabled
	void addUsersToDB() {
		for (int i=1; i<=10; i++) {
			User user = User.builder()
					.firstName("User" + i)
					.lastName("LastName" + i)
					.email("user" + i + "@example.com")
					.address(i + " Example St")
					.alerting(i%2==0)
					.energyAlertingThreshold(1000.0 + i)
					.build();
			userRepository.save(user);
		}
		log.info("Users added to database successfully");
	}

}
