package com.example.HealthFriends.repository;

import com.example.HealthFriends.entity.ExerciseTypeData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPAExerciseTypeRepository extends JpaRepository<ExerciseTypeData, Long> {
}
