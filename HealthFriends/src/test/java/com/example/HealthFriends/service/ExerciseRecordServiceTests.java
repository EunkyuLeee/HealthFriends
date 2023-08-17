package com.example.HealthFriends.service;

import com.example.HealthFriends.dto.exercise.ExerciseSaveDTO;
import com.example.HealthFriends.dto.exerciseRecord.ExerciseRecordSaveDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class ExerciseRecordServiceTests {
    @Autowired
    private ExerciseRecordService exerciseRecordService;

    @Test
    @DisplayName("운동 기록 등록 테스트")
    void saveTest(){
        ExerciseRecordSaveDTO exerciseRecordSaveDTO = ExerciseRecordSaveDTO.builder()
                .id(1L)
                .exerciseNo(1L)
//                .countExercise(10)
//                .setsExercise(3)
                .startExerciseTime(new Date())
                .endExerciseTime(new Date())
                .build();
        exerciseRecordService.save(exerciseRecordSaveDTO);
    }

    @Test
    @DisplayName("운동 기록 조회 테스트")
    void findAllTest(){
        System.out.println(exerciseRecordService.findAll());
    }

    @Test
    @DisplayName("운동 기록 exerciseRecordNo를 통한 단일 조회 테스트")
    void findByIdTest(){
        System.out.println(exerciseRecordService.findByExerciseRecordNo(1L));
    }

    @Test
    @DisplayName("사용자 id를 통한 운동 기록 리스트 조회 테스트")
    void findByUserEntityTest(){
        exerciseRecordService.findByUserId(1L).forEach(System.out::println);
    }

    @Test
    @DisplayName("운동 번호를 통한 운동 기록 리스트 조회 테스트")
    void findByExerciseNoTests(){
        System.out.println(exerciseRecordService.findByExerciseNo(1L));
    }
}
