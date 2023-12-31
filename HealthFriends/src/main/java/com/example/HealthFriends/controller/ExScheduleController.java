package com.example.HealthFriends.controller;

import com.example.HealthFriends.dto.DailySchedule;
import com.example.HealthFriends.dto.ExerciseScheduleDto;
import com.example.HealthFriends.dto.SimpleEx;
import com.example.HealthFriends.service.ScheduleService;
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

@Tag(name = "Exercise Schedule", description = "운동 일정 API")
@RestController
@RequiredArgsConstructor
public class ExScheduleController {
    private final ScheduleService scheduleService;

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<String> handleIllegalArgumentException(Exception exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.getMessage());
    }

    @ExceptionHandler({NoSuchObjectException.class})
    public ResponseEntity<String> handleNoSuchObjectException(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @Operation(summary = "get exercise schedules", description = "운동 일정 조회")
    @ApiResponse(responseCode = "200", description = "성공")
    @GetMapping("/api/schedule/get")
    public List<ExerciseScheduleDto> getAllExerciseSchedule() {
        return scheduleService.getAllExerciseSchedule();
    }

    @Operation(summary = "add exercise schedule", description = "새로운 일정 추가\n" +
            "(parameter: title, year, month, day, start_time, end_time, user_id)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스", content = @Content(schema = @Schema(implementation = ResponseEntity.class)))
    })
    @PostMapping("/api/schedule/post")
    public ExerciseScheduleDto addExerciseSchedule(ExerciseScheduleDto exerciseScheduleDto) throws NoSuchObjectException {
        exerciseScheduleDto.setId(null);
        exerciseScheduleDto.setComplete(false);
        return scheduleService.addExerciseSchedule(exerciseScheduleDto);
    }

    @Operation(summary = "get exercise schedule by date", description = "일별 운동 일정 조회\n" +
            "(parameter: /user_id/year/month/day)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스", content = @Content(schema = @Schema(implementation = ResponseEntity.class)))
    })
    @GetMapping("/api/schedule/get/{user_id}/{year}/{month}/{day}")
    public List<SimpleEx> getExerciseScheduleByDate(@PathVariable(name = "user_id") Long user_id,
                                                    @PathVariable(name = "year") Integer year,
                                                    @PathVariable(name = "month") Integer month,
                                                    @PathVariable(name = "day") Integer day) throws NoSuchObjectException {
        return scheduleService.getExerciseScheduleByDate(user_id, year, month, day);
    }

    @Operation(summary = "delete exercise schedule", description = "운동 일정 삭제\n" +
            "(parameter: /id)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스", content = @Content(schema = @Schema(implementation = ResponseEntity.class)))
    })
    @DeleteMapping("/api/schedule/delete/{id}")
    public ExerciseScheduleDto deleteExerciseSchedule(@PathVariable(name = "id") Long id) throws NoSuchObjectException {
        return scheduleService.deleteExerciseSchedule(id);
    }

    @Operation(summary = "get exercise schedule by id", description = "운동 일정 상세 조회\n" +
            "(parameter: /id)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스", content = @Content(schema = @Schema(implementation = ResponseEntity.class)))
    })
    @GetMapping("/api/schedule/get/{id}")
    public ExerciseScheduleDto getExerciseScheduleByDate(@PathVariable(name = "id") Long id) throws NoSuchObjectException {
        return scheduleService.getExerciseScheduleById(id);
    }

    @Operation(summary = "get exercise schedule by month", description = "월별 운동 일정 조회\n" +
            "(parameter: /user_id/year/month)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "403", description = "비정상적 파라미터", content = @Content(schema = @Schema(implementation = ResponseEntity.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스", content = @Content(schema = @Schema(implementation = ResponseEntity.class)))
    })
    @GetMapping("/api/schedule/get/{user_id}/{year}/{month}")
    public List<DailySchedule> getExerciseScheduleByMonth(@PathVariable(name = "user_id") Long user_id, 
                                                          @PathVariable(name = "year") Integer year, 
                                                          @PathVariable(name = "month") Integer month) throws NoSuchObjectException {
        return scheduleService.getExerciseScheduleByMonth(user_id, year, month);
    }

    @Operation(summary = "change schedule complete state", description = "일정 완료 상태 변경\n" +
            "(parameter: id)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스", content = @Content(schema = @Schema(implementation = ResponseEntity.class)))
    })
    @PutMapping("/api/schedule/put")
    public ExerciseScheduleDto changeExerciseScheduleComplete(Long id) throws NoSuchObjectException {
        return scheduleService.changeComplete(id);
    }
}
