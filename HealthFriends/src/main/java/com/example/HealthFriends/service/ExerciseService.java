package com.example.HealthFriends.service;

import com.example.HealthFriends.dto.ExerciseRecordDto;
import com.example.HealthFriends.dto.ExerciseTypeDto;

import java.rmi.NoSuchObjectException;
import java.util.List;

public interface ExerciseService {
    ExerciseRecordDto exerciseStart(Long uid, Long exno) throws NoSuchObjectException;

    ExerciseRecordDto exerciseEnd(ExerciseRecordDto exerciseRecordDto);

    ExerciseRecordDto exerciseRecord(ExerciseRecordDto exerciseRecordDto) throws NoSuchObjectException;

    List<ExerciseRecordDto> getExerciseRecord();

    ExerciseTypeDto addExType(ExerciseTypeDto exerciseTypeDto) throws NoSuchObjectException;

    List<String> sortedListByTime(Long exNo);

    List<ExerciseTypeDto> getAllExerciseTypes();

    void deleteExerciseRecord(Long id) throws NoSuchObjectException;

    List<ExerciseTypeDto> getExerciseTypesByUserId(Long userId) throws NoSuchObjectException;
}
