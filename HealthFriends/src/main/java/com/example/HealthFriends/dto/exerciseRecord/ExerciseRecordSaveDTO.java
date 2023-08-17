package com.example.HealthFriends.dto.exerciseRecord;

import com.example.HealthFriends.entity.ExerciseEntity;
import com.example.HealthFriends.entity.ExerciseRecordEntity;
import com.example.HealthFriends.entity.User;
import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ExerciseRecordSaveDTO {
    private String exerciseName;
    private Long id;
    private Date startExerciseTime;
    private Date endExerciseTime;
    private Integer setsExercise;
    private Integer countExercise;
    private Long exerciseNo;

    public ExerciseRecordEntity toEntity(){
        return ExerciseRecordEntity.builder()
                .startExerciseTime(startExerciseTime)
                .endExerciseTime(endExerciseTime)
                .setsExercise(setsExercise)
                .countExercise(countExercise)
                .exerciseEntity(ExerciseEntity.builder()
                        .exerciseNo(exerciseNo)
                        .build())
                .userEntity(User.builder()
                        .id(id)
                        .build())
                .build();
    }

}
