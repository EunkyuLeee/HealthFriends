package com.example.HealthFriends.service;

import com.example.HealthFriends.dto.DailySchedule;
import com.example.HealthFriends.dto.ExerciseScheduleDto;
import com.example.HealthFriends.dto.SimpleEx;

import java.rmi.NoSuchObjectException;
import java.util.List;

public interface ScheduleService {
    List<ExerciseScheduleDto> getAllExerciseSchedule();

    ExerciseScheduleDto addExerciseSchedule(ExerciseScheduleDto exerciseScheduleDto) throws NoSuchObjectException;

    List<SimpleEx> getExerciseScheduleByDate(ExerciseScheduleDto exerciseScheduleDto) throws NoSuchObjectException;

    ExerciseScheduleDto deleteExerciseSchedule(SimpleEx simpleEx) throws NoSuchObjectException;

    ExerciseScheduleDto getExerciseScheduleById(SimpleEx simpleEx) throws NoSuchObjectException;

    List<DailySchedule> getExerciseScheduleByMonth(ExerciseScheduleDto exerciseScheduleDto) throws NoSuchObjectException;
}
