package com.example.HealthFriends.repository;

import com.example.HealthFriends.dto.ExerciseRecordDto;
import com.example.HealthFriends.entity.ExerciseRecordData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JPAExerciseRepository extends JpaRepository<ExerciseRecordData, Long> {
}
