package com.example.HealthFriends.dto.exercise;

import com.example.HealthFriends.entity.ExerciseEntity;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ExerciseResponseDTO {
    private String exerciseName;

    public ExerciseResponseDTO(final ExerciseEntity entity){
        exerciseName = entity.getExerciseName();
    }
}
