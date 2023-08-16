package com.example.HealthFriends.repository;


import com.example.HealthFriends.entity.ExerciseRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRecordRepository extends JpaRepository<ExerciseRecordEntity, Long>{
    //유저 id를 통한 exerciseRecord 리스트 조회
//    List<ExerciseRecordEntity> findAllByUserId(Long userId);
}
