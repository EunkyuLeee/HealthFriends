package com.example.HealthFriends.repository;

import com.example.HealthFriends.entity.ExerciseRecordData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPAExerciseRepository extends JpaRepository<ExerciseRecordData, Long> {
}
