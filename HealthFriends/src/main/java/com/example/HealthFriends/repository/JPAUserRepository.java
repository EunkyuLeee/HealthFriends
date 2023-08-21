package com.example.HealthFriends.repository;

import com.example.HealthFriends.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPAUserRepository extends JpaRepository<UserData, Long> {
}
