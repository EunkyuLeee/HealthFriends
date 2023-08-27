package com.example.HealthFriends.dto;

import com.example.HealthFriends.entity.ExerciseRecord;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ExerciseRecordDto {

    private Long id;
    private Integer count;
    private Integer sets;
    private Timestamp startTime;
    private String exTime;
    private Long userId;
    private Long exerciseNo;

    public ExerciseRecord toEntity() {

        return ExerciseRecord.builder()
                .id(id)
                .startTime(startTime)
                .exTime(exTime)
                .count(count)
                .sets(sets)
                .userId(userId)
                .exerciseNo(exerciseNo).build();
    }

}

