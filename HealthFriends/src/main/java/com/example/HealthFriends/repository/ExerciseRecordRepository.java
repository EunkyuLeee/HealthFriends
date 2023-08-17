package com.example.HealthFriends.repository;


import com.example.HealthFriends.entity.ExerciseEntity;
import com.example.HealthFriends.entity.ExerciseRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseRecordRepository extends JpaRepository<ExerciseRecordEntity, Long>{
    //유저 id를 통한 exerciseRecord 리스트 조회
    List<ExerciseRecordEntity> findByUserEntity_id(Long userId);

    //exerciseRecordNo pk를 통한 운동 기록 단일 조회
    ExerciseRecordEntity findByExerciseRecordNo(Long exerciseRecordNo);

    //exerciseNo fk를 통한 운동 기록 리스트 조회
    List<ExerciseRecordEntity> findByExerciseEntity_ExerciseNo(Long exerciseNo);
}
