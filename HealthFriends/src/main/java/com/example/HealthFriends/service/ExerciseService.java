package com.example.HealthFriends.service;

import com.example.HealthFriends.dto.ExerciseRecordDto;
import com.example.HealthFriends.dto.ExerciseTypeDto;

import java.util.List;

public interface ExerciseService {
    ExerciseRecordDto exerciseStart(Long uid, Long exno);

    ExerciseRecordDto exerciseEnd(ExerciseRecordDto exerciseRecordDto);

    ExerciseRecordDto exerciseRecord(ExerciseRecordDto exerciseRecordDto);

    List<ExerciseRecordDto> getExerciseRecord();

    ExerciseTypeDto addExType(ExerciseTypeDto exerciseTypeDto);

    List<ExerciseTypeDto> getExerciseType();

    List<String> sortedListByTime(Long exNo);
}
