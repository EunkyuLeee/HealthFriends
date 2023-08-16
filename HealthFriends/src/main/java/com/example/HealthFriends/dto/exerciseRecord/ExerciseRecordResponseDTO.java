package com.example.HealthFriends.dto.exerciseRecord;

import com.example.HealthFriends.entity.ExerciseRecordEntity;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ExerciseRecordResponseDTO {
    private Long exerciseRecordNo;
    private Date startExerciseTime;
    private Date endExerciseTime;
    private Integer setsExercise;
    private Integer countExercise;

    public ExerciseRecordResponseDTO(final ExerciseRecordEntity entity){
        exerciseRecordNo = entity.getExerciseRecordNo();
        startExerciseTime = entity.getStartExerciseTime();
        endExerciseTime = entity.getEndExerciseTime();
        setsExercise = entity.getSetsExercise();
        countExercise = entity.getCountExercise();
    }

    public ExerciseRecordEntity toEntity(){
        return ExerciseRecordEntity.builder()
                .exerciseRecordNo(exerciseRecordNo)
                .startExerciseTime(startExerciseTime)
                .endExerciseTime(endExerciseTime)
                .setsExercise(setsExercise)
                .countExercise(countExercise)
                .build();
    }
}
