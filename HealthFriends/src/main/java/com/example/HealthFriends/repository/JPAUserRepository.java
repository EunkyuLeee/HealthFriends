package com.example.HealthFriends.repository;

import com.example.HealthFriends.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPAUserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
}
