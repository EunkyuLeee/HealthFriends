package com.example.HealthFriends.repository;

import com.example.HealthFriends.entity.ExerciseRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JPAExerciseRepository extends JpaRepository<ExerciseRecord, Long> {

    @Query(value = "select * from exercise_record where user_id = ?1 order by id",
            nativeQuery = true)
    List<ExerciseRecord> findByUserId(Long uid);

    @Query(value = "select * from exercise_record where (user_id = ?1 and exercise_no = ?2) order by id",
            nativeQuery = true)
    List<ExerciseRecord> findByUserIdAndExerciseNo(Long uid, Long exno);

    @Query(value = "select * from exercise_record " +
            "where (start_time >= ?1 and start_time < ?2) order by id",
            nativeQuery = true)
    List<ExerciseRecord> findByDate(String date1, String date2);

    List<ExerciseRecord> findByExerciseNo(Long exNo);
}
