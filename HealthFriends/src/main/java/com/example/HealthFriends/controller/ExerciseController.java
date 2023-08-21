package com.example.HealthFriends.controller;

import com.example.HealthFriends.dto.ExerciseRecordDto;
import com.example.HealthFriends.dto.ExerciseTypeDto;
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
    private boolean inExercise = false;
    private ExerciseRecordDto tmpDto;

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<String> handleIllegalArgumentException(Exception exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.getMessage());
    }

    @ExceptionHandler({NoSuchObjectException.class})
    public ResponseEntity<String> handleNoSuchObjectException(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @Operation(summary = "start exercise", description = "시간 기록형 운동 시작")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "403", description = "비정상적 파라미터", content = @Content(schema = @Schema(implementation = ResponseEntity.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스", content = @Content(schema = @Schema(implementation = ResponseEntity.class)))
    })
    @PostMapping("/api/startEx")
    public void exerciseStart(@RequestParam Long uid, @RequestParam Long exno) throws Exception {
        if (inExercise) {
            return;
        }
        inExercise = true;
        tmpDto = exerciseService.exerciseStart(uid, exno);
    }

    @Operation(summary = "end exercise", description = "시간 기록형 운동 종료")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "403", description = "비정상적 파라미터", content = @Content(schema = @Schema(implementation = ResponseEntity.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스", content = @Content(schema = @Schema(implementation = ResponseEntity.class)))
    })
    @PostMapping("/api/endEx")
    public ExerciseRecordDto exerciseEnd(@RequestParam String exerciseTime){
        if (!inExercise) {
            return null;
        }
        inExercise = false;

        tmpDto.setExTime(exerciseTime);

        return exerciseService.exerciseEnd(tmpDto);
    }

    @Operation(summary = "termination exercise record", description = "시간 기록형 운동 강제 종료")
    @ApiResponse(responseCode = "200", description = "성공")
    @GetMapping("/api/terminateEx")
    public boolean exerciseTerminate() {
        boolean ret;

        ret = inExercise;
        inExercise = false;
        tmpDto = null;

        return ret;
    }

    @Operation(summary = "record exercise", description = "횟수 증가형 운동 기록")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "403", description = "비정상적 파라미터", content = @Content(schema = @Schema(implementation = ResponseEntity.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스", content = @Content(schema = @Schema(implementation = ResponseEntity.class)))
    })
    @PostMapping("/api/recordEx")
    public ExerciseRecordDto exerciseRecord(@RequestParam Long uid, @RequestParam Long exno,
                                            @RequestParam Integer count, @RequestParam Integer set) throws NoSuchObjectException {
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
    @PostMapping("/api/addExType")
    public ExerciseTypeDto addExerciseType
            (@RequestParam String name, @RequestParam Character type, @RequestParam Long uid) throws NoSuchObjectException {
        ExerciseTypeDto exerciseTypeDto = new ExerciseTypeDto();

        exerciseTypeDto.setExName(name);
        exerciseTypeDto.setRecType(type);
        exerciseTypeDto.setCBy(uid);

        return exerciseService.addExType(exerciseTypeDto);
    }

    @Operation(summary = "get exercise type", description = "운동 종류 조회")
    @ApiResponse(responseCode = "200", description = "성공")
    @GetMapping("/api/getAllExTypes")
    public List<ExerciseTypeDto> getAllExerciseTypes() {
        return exerciseService.getAllExerciseTypes();
    }

    @Operation(summary = "delete exercise record", description = "운동 기록 삭제")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스", content = @Content(schema = @Schema(implementation = ResponseEntity.class)))
    })
    @DeleteMapping("/api/deleteEx")
    public void deleteExercise(@RequestParam Long exno) throws NoSuchObjectException {
        exerciseService.deleteExerciseRecord(exno);
    }

    @Operation(summary = "get exercise type", description = "사용자에 따른 운동 종류 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스", content = @Content(schema = @Schema(implementation = ResponseEntity.class)))
    })
    @GetMapping("/api/getExTypesByUserId")
    public List<ExerciseTypeDto> getExerciseTypesByUserId(@RequestParam Long uid) throws NoSuchObjectException {
        return exerciseService.getExerciseTypesByUserId(uid);
    }

    @Operation(summary = "ranking", description = "시간 순으로 정렬된 순위 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스", content = @Content(schema = @Schema(implementation = ResponseEntity.class)))
    })
    @GetMapping("/api/ranking")
    public List<String> sortedList(@RequestParam Long exNo) {
       return exerciseService.sortedListByTime(exNo);
    }
}
