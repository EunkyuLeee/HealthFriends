package com.example.HealthFriends.repository;

import com.example.HealthFriends.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JPAExerciseTypeRepository extends JpaRepository<Exercise, Long> {

    @Query(value = "select * from exercise where (exercise.created_by = 0 or exercise.created_by = ?1)",
            nativeQuery = true)
    List<Exercise> findByUserId(Long userId);
}
