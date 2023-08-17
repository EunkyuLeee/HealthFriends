package com.example.HealthFriends.dto;

import com.example.HealthFriends.entity.ExerciseData;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ExerciseDto {

    private Long id;
    private Integer count;
    private Integer sets;
    private Timestamp startTime;
    private Timestamp endTime;
    private Long userId;
    private Long exerciseNo;
    private Timestamp regdate;

    public ExerciseData toEntity() {

        return ExerciseData.builder()
                .startTime(startTime)
                .endTime(endTime)
                .count(count)
                .sets(sets)
                .userId(userId)
                .exerciseNo(exerciseNo)
                .regdate(regdate).build();
    }

}
