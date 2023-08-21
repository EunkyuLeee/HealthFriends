package com.example.HealthFriends.repository;

import com.example.HealthFriends.entity.ExerciseTypeData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JPAExerciseTypeRepository extends JpaRepository<ExerciseTypeData, Long> {

    @Query(value = "select * from tbl_exercise where (tbl_exercise.created_by = 0 or tbl_exercise.created_by = ?1)", nativeQuery = true)
    List<ExerciseTypeData> findByUserId(Long userId);
}
