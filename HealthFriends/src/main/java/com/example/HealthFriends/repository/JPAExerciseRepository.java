package com.example.HealthFriends.repository;

import com.example.HealthFriends.entity.ExRanking;
import com.example.HealthFriends.entity.ExDailyRecord;
import com.example.HealthFriends.entity.ExerciseRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JPAExerciseRepository extends JpaRepository<ExerciseRecord, Long> {

    @Query(value = "select R.id, R.start_time, R.exercise_time, R.sets, R.count, E.exercise_name " +
            "from exercise_record as R " +
            "join exercise as E on R.exercise_no = E.id " +
            "where R.user_id = ?1 " +
            "order by R.id",
            nativeQuery = true)
    List<ExDailyRecord> findByUserId(Long uid);

    @Query(value = "select R.id, R.start_time, R.exercise_time, R.sets, R.count, E.exercise_name " +
            "from exercise_record as R " +
            "join exercise as E on R.exercise_no = E.id " +
            "where (R.user_id = ?1 and R.exercise_no = ?2) " +
            "order by R.id",
            nativeQuery = true)
    List<ExDailyRecord> findByUserIdAndExerciseNo(Long uid, Long exno);

    @Query(value = "select R.id, R.start_time, R.exercise_time, R.sets, R.count, E.exercise_name " +
            "from exercise_record as R " +
            "join exercise as E on R.exercise_no = E.id " +
            "where (R.start_time >= ?2 and R.start_time < ?3 and R.user_id = ?1) " +
            "order by R.id",
            nativeQuery = true)
    List<ExDailyRecord> findByDate(Long uid, String date1, String date2);

    @Query(value = "select U.id, U.name, R.exercise_time from exercise_record as R join user as U " +
            "on R.user_id = U.id " +
            "where (R.exercise_no = ?1 and R.exercise_time IS NOT NULL) " +
            "order by R.exercise_time desc",
            nativeQuery = true)
    List<ExRanking> getRankList(Long exNo);
}
