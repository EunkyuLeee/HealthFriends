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
    public List<SimpleEx> getExerciseScheduleByDate(Long user_id, Integer year, Integer month, Integer day)
            throws NoSuchObjectException {
        Optional<UserData> user = jpaUserRepository.findById(user_id);
        if (user.isEmpty()) {
            throw new NoSuchObjectException("There is NO such user_id!!");
        }

        List<ExerciseScheduleData> byDate = jpaScheduleRepository.findByDate(user_id, year, month, day);
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
    public ExerciseScheduleDto deleteExerciseSchedule(Long id) throws NoSuchObjectException {
        ExerciseScheduleDto byId = getExerciseScheduleById(id);

        jpaScheduleRepository.deleteById(id);

        return byId;
    }

    @Override
    public ExerciseScheduleDto getExerciseScheduleById(Long id) throws NoSuchObjectException {
        Optional<ExerciseScheduleData> byId = jpaScheduleRepository.findById(id);
        if (byId.isEmpty()) {
            throw new NoSuchObjectException("There is NO such exercise schedule ID!!");
        }

        ExerciseScheduleDto dto = new ExerciseScheduleDto();

        ExerciseScheduleData data = byId.get();

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
    public List<DailySchedule> getExerciseScheduleByMonth(Long user_id, Integer year, Integer month) throws NoSuchObjectException {
        Optional<UserData> user = jpaUserRepository.findById(user_id);
        if (user.isEmpty()) {
            throw new NoSuchObjectException("There is NO such user_id!!");
        }
        if (month > 13 || month < 1) {
            throw new IllegalArgumentException("Data \"month\" is NOT valid");
        }

        List<ExSchedule> byMonth = jpaScheduleRepository.findByMonth(user_id, year, month);
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

    @Override
    public ExerciseScheduleDto changeComplete(Long id) throws NoSuchObjectException {
        ExerciseScheduleDto dto = getExerciseScheduleById(id);

        dto.setComplete(!(dto.getComplete()));

        jpaScheduleRepository.save(dto.toEntity());

        return dto;
    }
}
