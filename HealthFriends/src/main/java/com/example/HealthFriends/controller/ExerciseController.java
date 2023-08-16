package com.example.HealthFriends.controller;

import com.example.HealthFriends.entity.ExerciseData;
import com.example.HealthFriends.service.ExerciseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Exercise", description = "운동 기록 API")
@RestController
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;
    private boolean inExercise = false;
    private ExerciseData tmpData;

    @Operation(summary = "start exercise", description = "시간 기록형 운동 시작")
    @ApiResponse(responseCode = "200", description = "성공")
    @GetMapping("/api/exStart")
    public void exerciseStart(@RequestParam Long uid, @RequestParam Long exno) {
        if (inExercise) {
            return;
        }
        inExercise = true;
        tmpData = exerciseService.exerciseStart(uid, exno);
    }

    @Operation(summary = "end exercise", description = "시간 기록형 운동 종료")
    @ApiResponse(responseCode = "200", description = "성공")
    @GetMapping("/api/exEnd")
    public ExerciseData exerciseEnd() {
        if (!inExercise) {
            return null;
        }
        inExercise = false;
        return exerciseService.exerciseEnd(tmpData);
    }

    @Operation(summary = "record exercise", description = "횟수 증가형 운동 기록")
    @ApiResponse(responseCode = "200", description = "성공")
    @GetMapping("/api/exRecord")
    public ExerciseData exerciseRecord(@RequestParam Long uid, @RequestParam Long exno,
                               @RequestParam Integer count, @RequestParam Integer set) {
        return exerciseService.exerciseRecord(uid, exno, count, set);
    }
}