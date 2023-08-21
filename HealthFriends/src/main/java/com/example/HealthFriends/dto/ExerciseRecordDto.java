package com.example.HealthFriends.dto;

import com.example.HealthFriends.entity.ExerciseRecordData;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ExerciseRecordDto {

    private Long id;
    private Integer count;
    private Integer sets;
    private Timestamp startTime;
    private Timestamp endTime;
    private Long userId;
    private Long exerciseNo;
    private String exerciseTime;
    private Timestamp regdate;

    public ExerciseRecordData toEntity() {

        return ExerciseRecordData.builder()
                .startTime(startTime)
                .endTime(endTime)
                .count(count)
                .sets(sets)
                .userId(userId)
                .exerciseNo(exerciseNo)
                .exerciseTime(exerciseTime)
                .regdate(regdate).build();
    }

}
