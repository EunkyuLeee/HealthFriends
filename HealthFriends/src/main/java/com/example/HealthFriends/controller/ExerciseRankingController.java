package com.example.HealthFriends.controller;

import com.example.HealthFriends.dto.Ranking;
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
            @ApiResponse(responseCode = "403", description = "비정상적 파라미터", content = @Content(schema = @Schema(implementation = ResponseEntity.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스", content = @Content(schema = @Schema(implementation = ResponseEntity.class)))
    })
    @GetMapping("/api/ranking/{exercise_no}")
    public List<Ranking> getRanking(@PathVariable(name = "exercise_no") Long exNo) throws NoSuchObjectException {
        return exerciseService.getRanking(exNo);
    }

    @Operation(summary = "ranking by user", description = "해당 회원의 순위 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스", content = @Content(schema = @Schema(implementation = ResponseEntity.class)))
    })
    @GetMapping("/api/ranking/{exercise_no}/{user_id}")
    public Ranking getRankingByUserId(@PathVariable(name = "exercise_no") Long exNo,
                                      @PathVariable(name = "user_id") Long uid) throws NoSuchObjectException {
        return exerciseService.getRankingByUserId(exNo, uid);
    }

}
