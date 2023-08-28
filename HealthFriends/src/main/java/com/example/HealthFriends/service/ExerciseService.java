package com.example.HealthFriends.service;

import com.example.HealthFriends.dto.ExRecordByTypeDto;
import com.example.HealthFriends.dto.ExerciseRecordDto;
import com.example.HealthFriends.dto.ExerciseTypeDto;
import com.example.HealthFriends.dto.RecordingDto;

import java.rmi.NoSuchObjectException;
import java.util.List;

public interface ExerciseService {

    List<ExerciseTypeDto> getExerciseTypesByUserId(Long userId) throws NoSuchObjectException;

    ExerciseTypeDto addExType(ExerciseTypeDto exerciseTypeDto) throws NoSuchObjectException;

    ExerciseRecordDto exerciseStart(Long uid, Long exid) throws NoSuchObjectException;

    ExerciseRecordDto exerciseTerminate(Long recordId) throws NoSuchObjectException;

    ExerciseRecordDto exerciseRecord(RecordingDto recordingDto) throws NoSuchObjectException;

    List<ExRecordByTypeDto> getExerciseRecord(Long user_id, Long exercise_no) throws NoSuchObjectException;

    List<ExRecordByTypeDto> getDailyExerciseRecordById(Long id) throws NoSuchObjectException;

    List<String> sortedListByTime(Long exNo);
}
