package com.example.HealthFriends.dto.exerciseRecord;

import com.example.HealthFriends.entity.ExerciseRecordEntity;
import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ExerciseRecordSaveDTO {
    private String exerciseName;
//    private String id;
    private Date startExerciseTime;
    private Date endExerciseTime;
    private Integer setsExercise;
    private Integer countExercise;

    public ExerciseRecordEntity toEntity(){
        return ExerciseRecordEntity.builder()
                .startExerciseTime(startExerciseTime)
                .endExerciseTime(endExerciseTime)
                .setsExercise(setsExercise)
                .countExercise(countExercise)
                .build();
    }

}
