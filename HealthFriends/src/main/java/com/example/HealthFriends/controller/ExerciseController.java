package com.example.HealthFriends.controller;

import com.example.HealthFriends.dto.ExRecordByTypeDto;
import com.example.HealthFriends.dto.ExerciseRecordDto;
import com.example.HealthFriends.dto.ExerciseTypeDto;
import com.example.HealthFriends.dto.RecordingDto;
import com.example.HealthFriends.service.ExerciseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.NoSuchObjectException;
import java.util.List;

@Tag(name = "Exercise", description = "운동 기록 API")
@RestController
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<String> handleIllegalArgumentException(Exception exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.getMessage());
    }

    @ExceptionHandler({NoSuchObjectException.class})
    public ResponseEntity<String> handleNoSuchObjectException(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @Operation(summary = "get exercise type", description = "사용자에 따른 운동 종류 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스", content = @Content(schema = @Schema(implementation = ResponseEntity.class)))
    })
    @GetMapping("/api/exercise/getExTypes/{user_id}")
    public List<ExerciseTypeDto> getExerciseTypesByUserId(@PathVariable(name = "user_id") Long user_id) throws NoSuchObjectException {
        return exerciseService.getExerciseTypesByUserId(user_id);
    }

    @Operation(summary = "add exercise type", description = "새로운 운동 추가\n" +
            "(parameter: exercise_name, created_by)")
    @ApiResponse(responseCode = "200", description = "성공")
    @PostMapping("/api/exercise/postExType")
    public ExerciseTypeDto addExerciseType(ExerciseTypeDto exerciseTypeDto) throws NoSuchObjectException {
        return exerciseService.addExType(exerciseTypeDto);
    }

    @Operation(summary = "start exercise", description = "운동 시작")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스", content = @Content(schema = @Schema(implementation = ResponseEntity.class)))
    })
    @PostMapping("/api/exercise/start")
    public ExerciseRecordDto exerciseStart(Long user_id, Long exercise_id) throws NoSuchObjectException {
        return exerciseService.exerciseStart(user_id, exercise_id);
    }

    @Operation(summary = "terminate exercise record", description = "운동 기록 강제 종료")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스", content = @Content(schema = @Schema(implementation = ResponseEntity.class)))
    })
    @GetMapping("/api/exercise/terminate/{record_id}")
    public ExerciseRecordDto exerciseStart(@PathVariable(name = "record_id") Long record_id) throws NoSuchObjectException {
        return exerciseService.exerciseTerminate(record_id);
    }

    @Operation(summary = "record exercise", description = "운동 기록")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "403", description = "비정상적 파라미터", content = @Content(schema = @Schema(implementation = ResponseEntity.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스", content = @Content(schema = @Schema(implementation = ResponseEntity.class)))
    })
    @PutMapping("/api/exercise/record")
    public ExerciseRecordDto exerciseRecord(RecordingDto recordingDto) throws NoSuchObjectException {

        return exerciseService.exerciseRecord(recordingDto);
    }

    @Operation(summary = "get exercise records", description = "운동 기록 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스", content = @Content(schema = @Schema(implementation = ResponseEntity.class)))
    })
    @GetMapping("/api/exercise/getRecords/{user_id}/{exercise_no}")
    public List<ExRecordByTypeDto> getExerciseRecords(@PathVariable(name = "user_id") Long user_id,
                                                      @PathVariable(name = "exercise_no") Long exercise_no) throws NoSuchObjectException {
        return exerciseService.getExerciseRecord(user_id, exercise_no);
    }

    @Operation(summary = "get daily exercise record", description = "일별 운동 상세 기록 조회 (by 운동 기록 id)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스", content = @Content(schema = @Schema(implementation = ResponseEntity.class)))
    })
    @GetMapping("/api/exercise/getDetail/{id}")
    public List<ExRecordByTypeDto> getDailyExerciseRecordsById(@PathVariable(name = "id") Long id) throws NoSuchObjectException {
        return exerciseService.getDailyExerciseRecordById(id);
    }

}
