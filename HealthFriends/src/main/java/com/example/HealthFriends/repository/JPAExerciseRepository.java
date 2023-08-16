package com.example.HealthFriends.repository;

import com.example.HealthFriends.entity.ExerciseData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPAExerciseRepository extends JpaRepository<ExerciseData, Long> {
}
