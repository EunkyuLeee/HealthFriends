package com.example.HealthFriends.dto;

import com.example.HealthFriends.entity.ExerciseTypeData;
import lombok.Data;

@Data
public class ExerciseTypeDto {
    private Long exNo;
    private String exName;
    private Character recType;
    private Long cBy;

    public ExerciseTypeData toEntity() {

        return ExerciseTypeData.builder()
                .exName(exName)
                .recType(recType)
                .cBy(cBy)
                .build();
    }

}
