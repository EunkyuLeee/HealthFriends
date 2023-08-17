package com.example.HealthFriends.service;

import com.example.HealthFriends.dto.ExerciseDto;
import com.example.HealthFriends.entity.ExerciseData;

import java.util.List;

public interface ExerciseService {
    ExerciseDto exerciseStart(Long uid, Long exno);

    ExerciseDto exerciseEnd(ExerciseDto exerciseDto);

    ExerciseDto exerciseRecord(ExerciseDto exerciseDto);

    List<ExerciseDto> exerciseGet();
}
