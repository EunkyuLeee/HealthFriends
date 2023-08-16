package com.example.HealthFriends.service.impl;

import com.example.HealthFriends.entity.ExerciseData;
import com.example.HealthFriends.repository.JPAExerciseRepository;
import com.example.HealthFriends.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    @Autowired
    private JPAExerciseRepository jpaExerciseRepository;
    @Override
    public ExerciseData exerciseStart(Long uid, Long exno) {
        ExerciseData exerciseData = new ExerciseData();

        exerciseData.setUserId(uid);
        exerciseData.setExerciseNo(exno);
        exerciseData.setStartTime(new Timestamp(System.currentTimeMillis()));

        return exerciseData;
    }

    @Override
    public ExerciseData exerciseEnd(ExerciseData exerciseData) {
        Timestamp ts = new Timestamp(System.currentTimeMillis());

        exerciseData.setEndTime(ts);
        exerciseData.setRegdate(ts);

        jpaExerciseRepository.save(exerciseData);
        return exerciseData;
    }

    @Override
    public ExerciseData exerciseRecord(Long uid, Long exno, Integer count, Integer set) {

        ExerciseData exerciseData = new ExerciseData();

        exerciseData.setUserId(uid);
        exerciseData.setExerciseNo(exno);
        exerciseData.setCount(count);
        exerciseData.setSets(set);
        exerciseData.setRegdate(new Timestamp(System.currentTimeMillis()));

        jpaExerciseRepository.save(exerciseData);

        return exerciseData;
    }
}
