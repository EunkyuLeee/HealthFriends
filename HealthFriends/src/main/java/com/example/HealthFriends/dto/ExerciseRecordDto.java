package com.example.HealthFriends.dto;

import com.example.HealthFriends.entity.ExerciseRecordData;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ExerciseRecordDto {

    private Long id;
    private Integer count;
    private Integer sets;
    private Timestamp startTime;
    private Timestamp endTime;
    private String exTime;
    private Long userId;
    private Long exerciseNo;
    private Timestamp regdate;

    public ExerciseRecordData toEntity() {

        return ExerciseRecordData.builder()
                .startTime(startTime)
                .endTime(endTime)
                .exTime(exTime)
                .count(count)
                .sets(sets)
                .userId(userId)
                .exerciseNo(exerciseNo)
                .regdate(regdate).build();
    }

}

