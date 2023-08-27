package com.example.HealthFriends;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.TimeZone;

@SpringBootApplication
public class HealthFriendsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthFriendsApplication.class, args);
	}

}
