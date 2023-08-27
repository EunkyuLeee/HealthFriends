package com.example.HealthFriends.dto;

import com.example.HealthFriends.entity.Exercise;
import lombok.Data;

@Data
public class ExerciseTypeDto {
    private Long id;
    private String exercise_name;
    private Long created_by;

    public Exercise toEntity() {

        return Exercise.builder()
                .id(id)
                .exName(exercise_name)
                .cBy(created_by)
                .build();
    }

}
