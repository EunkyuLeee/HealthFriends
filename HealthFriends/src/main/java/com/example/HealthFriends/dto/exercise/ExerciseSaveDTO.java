package com.example.HealthFriends.dto.exercise;

import com.example.HealthFriends.entity.ExerciseEntity;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ExerciseSaveDTO {
    private Long exerciseNo;
    private String exerciseName;

    public ExerciseSaveDTO(final ExerciseEntity entity){
        exerciseNo = entity.getExerciseNo();
        exerciseName = entity.getExerciseName();
    }

    public ExerciseEntity toEntity(){
        return ExerciseEntity.builder()
                .exerciseNo(exerciseNo)
                .exerciseName(exerciseName)
                .build();
    }
}
