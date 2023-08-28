package com.example.HealthFriends.repository;

import com.example.HealthFriends.entity.ExSchedule;
import com.example.HealthFriends.entity.ExerciseSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JPAScheduleRepository extends JpaRepository<ExerciseSchedule, Long> {

    @Query(value = "select * from exercise_schedule " +
            "where (exercise_schedule.user_id = ?1 and exercise_schedule.year = ?2 and " +
            "exercise_schedule.month = ?3 and exercise_schedule.day = ?4) " +
            "order by exercise_schedule.id",
            nativeQuery = true)
    List<ExerciseSchedule> findByDate(long uid, int y, int m, int d);

    @Query(value = "select exercise_schedule.day, count(*) as count from exercise_schedule " +
            "where exercise_schedule.user_id = ?1 and exercise_schedule.year = ?2 and exercise_schedule.month = ?3 " +
            "group by exercise_schedule.day order by exercise_schedule.day",
            nativeQuery = true)
    List<ExSchedule> findByMonth(Long uid, int y, int m);
}
