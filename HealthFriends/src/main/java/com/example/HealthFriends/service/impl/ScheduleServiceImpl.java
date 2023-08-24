package com.example.HealthFriends.service.impl;

import com.example.HealthFriends.dto.DailySchedule;
import com.example.HealthFriends.dto.ExerciseScheduleDto;
import com.example.HealthFriends.dto.SimpleEx;
import com.example.HealthFriends.entity.ExSchedule;
import com.example.HealthFriends.entity.ExerciseScheduleData;
import com.example.HealthFriends.entity.UserData;
import com.example.HealthFriends.repository.JPAScheduleRepository;
import com.example.HealthFriends.repository.JPAUserRepository;
import com.example.HealthFriends.service.ScheduleService;
import org.springframework.stereotype.Service;

import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final JPAUserRepository jpaUserRepository;
    private final JPAScheduleRepository jpaScheduleRepository;

    public ScheduleServiceImpl(JPAUserRepository jpaUserRepository, JPAScheduleRepository jpaScheduleRepository) {
        this.jpaUserRepository = jpaUserRepository;
        this.jpaScheduleRepository = jpaScheduleRepository;
    }

    @Override
    public List<ExerciseScheduleDto> getAllExerciseSchedule() {
        List<ExerciseScheduleData> all = jpaScheduleRepository.findAll();

        List<ExerciseScheduleDto> dtoList = new ArrayList<>();

        for (ExerciseScheduleData esd : all) {
            ExerciseScheduleDto tmp = new ExerciseScheduleDto();

            tmp.setId(esd.getId());
            tmp.setTitle(esd.getTitle());
            tmp.setYear(esd.getYear());
            tmp.setMonth(esd.getMonth());
            tmp.setDay(esd.getDay());
            tmp.setStart_time(esd.getSTime());
            tmp.setEnd_time(esd.getETime());
            tmp.setComplete(esd.getComplete());
            tmp.setUser_id(esd.getUid());

            dtoList.add(tmp);
        }

        return dtoList;
    }

    @Override
    public ExerciseScheduleDto addExerciseSchedule(ExerciseScheduleDto exerciseScheduleDto) throws NoSuchObjectException {
        Optional<UserData> user = jpaUserRepository.findById(exerciseScheduleDto.getUser_id());
        if (user.isEmpty()) {
            throw new NoSuchObjectException("There is NO such user_id!!");
        }
        jpaScheduleRepository.save(exerciseScheduleDto.toEntity());
        return exerciseScheduleDto;
    }

    @Override
    public List<SimpleEx> getExerciseScheduleByDate(ExerciseScheduleDto exerciseScheduleDto) throws NoSuchObjectException {
        Optional<UserData> user = jpaUserRepository.findById(exerciseScheduleDto.getUser_id());
        if (user.isEmpty()) {
            throw new NoSuchObjectException("There is NO such user_id!!");
        }

        long uid = exerciseScheduleDto.getUser_id();
        int y = exerciseScheduleDto.getYear();
        int m = exerciseScheduleDto.getMonth();
        int d = exerciseScheduleDto.getDay();

        List<ExerciseScheduleData> byDate = jpaScheduleRepository.findByDate(uid, y, m, d);
        List<SimpleEx> ret = new ArrayList<>();

        for (ExerciseScheduleData esd : byDate) {
            SimpleEx tmp = new SimpleEx();

            tmp.setId(esd.getId());
            tmp.setTitle(esd.getTitle());

            ret.add(tmp);
        }

        return ret;
    }

    @Override
    public ExerciseScheduleDto deleteExerciseSchedule(SimpleEx simpleEx) throws NoSuchObjectException {
        ExerciseScheduleDto byId = getExerciseScheduleById(simpleEx);

        jpaScheduleRepository.deleteById(simpleEx.getId());

        return byId;
    }

    @Override
    public ExerciseScheduleDto getExerciseScheduleById(SimpleEx simpleEx) throws NoSuchObjectException {
        Optional<ExerciseScheduleData> id = jpaScheduleRepository.findById(simpleEx.getId());
        if (id.isEmpty()) {
            throw new NoSuchObjectException("There is NO such exercise schedule ID!!");
        }

        ExerciseScheduleDto dto = new ExerciseScheduleDto();

        ExerciseScheduleData data = id.get();

        dto.setId(data.getId());
        dto.setTitle(data.getTitle());
        dto.setYear(data.getYear());
        dto.setMonth(data.getMonth());
        dto.setDay(data.getDay());
        dto.setComplete(data.getComplete());
        dto.setStart_time(data.getSTime());
        dto.setEnd_time(data.getETime());
        dto.setUser_id(data.getUid());

        return dto;
    }

    @Override
    public List<DailySchedule> getExerciseScheduleByMonth(ExerciseScheduleDto exerciseScheduleDto) throws NoSuchObjectException {
        Optional<UserData> user = jpaUserRepository.findById(exerciseScheduleDto.getUser_id());
        if (user.isEmpty()) {
            throw new NoSuchObjectException("There is NO such user_id!!");
        }
        if (exerciseScheduleDto.getMonth() > 13 || exerciseScheduleDto.getMonth() < 1) {
            throw new IllegalArgumentException("Data \"month\" is NOT valid");
        }

        long uid = exerciseScheduleDto.getUser_id();
        int y = exerciseScheduleDto.getYear();
        int m = exerciseScheduleDto.getMonth();

        List<ExSchedule> byMonth = jpaScheduleRepository.findByMonth(uid, y, m);
        List<DailySchedule> ret = new ArrayList<>();

        for (int i = 1; i <= 31; i++) {
            DailySchedule tmp = new DailySchedule();
            tmp.setDay(i);
            tmp.setCount(0L);
            ret.add(tmp);
        }

        for (ExSchedule ebd : byMonth) {
            ret.get(ebd.getDay() - 1).setCount(ebd.getCount());
        }

        return ret;
    }
}
