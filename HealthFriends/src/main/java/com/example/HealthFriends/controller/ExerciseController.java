package com.example.HealthFriends.controller;

import com.example.HealthFriends.dto.ExerciseRecordDto;
import com.example.HealthFriends.dto.ExerciseTypeDto;
import com.example.HealthFriends.service.ExerciseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Exercise", description = "운동 기록 API")
@RestController
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;
    private boolean inExercise = false;
    private ExerciseRecordDto tmpDto;

    @Operation(summary = "start exercise", description = "시간 기록형 운동 시작")
    @ApiResponse(responseCode = "200", description = "성공")
    @PutMapping("/api/exStart")
    public void exerciseStart(@RequestParam Long uid, @RequestParam Long exno) {
        if (inExercise) {
            return;
        }
        inExercise = true;
        tmpDto = exerciseService.exerciseStart(uid, exno);
    }

    @Operation(summary = "end exercise", description = "시간 기록형 운동 종료")
    @ApiResponse(responseCode = "200", description = "성공")
    @PutMapping("/api/exEnd")
    public ExerciseRecordDto exerciseEnd() {
        if (!inExercise) {
            return null;
        }
        inExercise = false;
        return exerciseService.exerciseEnd(tmpDto);
    }

    @Operation(summary = "record exercise", description = "횟수 증가형 운동 기록")
    @ApiResponse(responseCode = "200", description = "성공")
    @PutMapping("/api/exRecord")
    public ExerciseRecordDto exerciseRecord(@RequestParam Long uid, @RequestParam Long exno,
                                            @RequestParam Integer count, @RequestParam Integer set) {
        ExerciseRecordDto exerciseRecordDto = new ExerciseRecordDto();

        exerciseRecordDto.setUserId(uid);
        exerciseRecordDto.setExerciseNo(exno);
        exerciseRecordDto.setCount(count);
        exerciseRecordDto.setSets(set);

        return exerciseService.exerciseRecord(exerciseRecordDto);
    }

    @Operation(summary = "get exercise records", description = "운동 기록 조회")
    @ApiResponse(responseCode = "200", description = "성공")
    @GetMapping("/api/getExRecords")
    public List<ExerciseRecordDto> getExerciseRecords() {
        return exerciseService.getExerciseRecord();
    }

    @Operation(summary = "add exercise type", description = "새로운 운동 추가")
    @ApiResponse(responseCode = "200", description = "성공")
    @PutMapping("/api/addEx")
    public ExerciseTypeDto addExerciseType(@RequestParam String name, @RequestParam Character type) {
        ExerciseTypeDto exerciseTypeDto = new ExerciseTypeDto();

        exerciseTypeDto.setExName(name);
        exerciseTypeDto.setRecType(type);

        return exerciseService.addExType(exerciseTypeDto);
    }

    @Operation(summary = "get exercise type", description = "운동 종류 조회")
    @ApiResponse(responseCode = "200", description = "성공")
    @GetMapping("/api/getExType")
    public List<ExerciseTypeDto> getExerciseTypes() {
        return exerciseService.getExerciseType();
    }

}
