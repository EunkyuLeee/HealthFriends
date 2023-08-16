package com.example.HealthFriends.service;

import com.example.HealthFriends.entity.ExerciseData;

public interface ExerciseService {
    ExerciseData exerciseStart(Long uid, Long exno);

    ExerciseData exerciseEnd(ExerciseData exerciseData);

    ExerciseData exerciseRecord(Long uid, Long exno, Integer count, Integer set);
}
