package com.example.HealthFriends.service;

import com.example.HealthFriends.dto.DailySchedule;
import com.example.HealthFriends.dto.ExerciseScheduleDto;
import com.example.HealthFriends.dto.SimpleEx;

import java.rmi.NoSuchObjectException;
import java.util.List;

public interface ScheduleService {
    List<ExerciseScheduleDto> getAllExerciseSchedule();

    ExerciseScheduleDto addExerciseSchedule(ExerciseScheduleDto exerciseScheduleDto) throws NoSuchObjectException;

    List<SimpleEx> getExerciseScheduleByDate(Long user_id, Integer year, Integer month, Integer day) throws NoSuchObjectException;

    ExerciseScheduleDto deleteExerciseSchedule(Long id) throws NoSuchObjectException;

    ExerciseScheduleDto getExerciseScheduleById(Long id) throws NoSuchObjectException;

    List<DailySchedule> getExerciseScheduleByMonth(Long user_id, Integer year, Integer month) throws NoSuchObjectException;

    ExerciseScheduleDto changeComplete(Long id) throws NoSuchObjectException;
}
