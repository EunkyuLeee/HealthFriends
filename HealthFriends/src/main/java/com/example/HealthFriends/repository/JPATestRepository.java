package com.example.HealthFriends.repository;

import com.example.HealthFriends.entity.testData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JPATestRepository extends JpaRepository<testData, Integer> {
}
