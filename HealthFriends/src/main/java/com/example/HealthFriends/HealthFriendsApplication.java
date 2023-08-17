package com.example.HealthFriends;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class HealthFriendsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthFriendsApplication.class, args);
	}

}
