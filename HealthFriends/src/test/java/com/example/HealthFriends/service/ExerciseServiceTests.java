package com.example.HealthFriends.service;

import com.example.HealthFriends.dto.exercise.ExerciseSaveDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ExerciseServiceTests {
    @Autowired
    private ExerciseService exerciseService;

    @Test
    @DisplayName("운동 등록 테스트")
    void saveTest(){
        ExerciseSaveDTO exerciseSaveDto = ExerciseSaveDTO.builder()
                .exerciseName("테스트 윗몸 일으키기2")
                .build();
        exerciseService.save(exerciseSaveDto);
    }

    @Test
    @DisplayName("운동 조회 테스트")
    void findAllTest(){
        System.out.println(exerciseService.findAll());
    }

    @Test
    @DisplayName("운동 단일 조회 테스트")
    void findByIdTest(){
        System.out.println(exerciseService.findById(1L));
    }

//    @Test
//    @DisplayName("운동 수정 테스트")
//    void updateTest() {
//        ExerciseUpdateDTO exerciseUpdateDTO = ExerciseUpdateDTO.builder()
//                .exerciseName("테스트 윗몸 일으키기")
//                .build();
//        exerciseService.update
//    }

    @Test
    @DisplayName("운동 삭제 테스트")
    void deleteTest(){
        exerciseService.delete(6L);
    }

}
