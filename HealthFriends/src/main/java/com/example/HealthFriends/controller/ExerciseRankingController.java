package com.example.HealthFriends.controller;

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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.rmi.NoSuchObjectException;
import java.util.List;

@Tag(name = "Exercise Ranking", description = "운동 순위 API")
@RestController
@RequiredArgsConstructor
public class ExerciseRankingController {

    private final ExerciseService exerciseService;

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<String> handleIllegalArgumentException(Exception exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.getMessage());
    }

    @ExceptionHandler({NoSuchObjectException.class})
    public ResponseEntity<String> handleNoSuchObjectException(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
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
