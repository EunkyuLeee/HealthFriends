package com.example.HealthFriends.repository;

import com.example.HealthFriends.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JPAUserRepository extends JpaRepository<User, Long> {
    User findByName(String name);

    @Query(value = "SELECT * FROM user u WHERE u.id = :id", nativeQuery = true)
    User findById_(Long id);

}
