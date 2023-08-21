package com.example.HealthFriends.service.impl;

import com.example.HealthFriends.dto.ExerciseRecordDto;
import com.example.HealthFriends.dto.ExerciseTypeDto;
import com.example.HealthFriends.entity.ExerciseRecordData;
import com.example.HealthFriends.entity.ExerciseTypeData;
import com.example.HealthFriends.entity.UserData;
import com.example.HealthFriends.repository.JPAExerciseRepository;
import com.example.HealthFriends.repository.JPAExerciseTypeRepository;
import com.example.HealthFriends.repository.JPAUserRepository;
import com.example.HealthFriends.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.NoSuchObjectException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final JPAExerciseRepository jpaExerciseRepository;
    private final JPAExerciseTypeRepository jpaExerciseTypeRepository;
    private final JPAUserRepository jpaUserRepository;

    @Autowired
    public ExerciseServiceImpl
            (JPAExerciseRepository jpaExerciseRepository,
             JPAExerciseTypeRepository jpaExerciseTypeRepository,
             JPAUserRepository jpaUserRepository) {
        this.jpaExerciseRepository = jpaExerciseRepository;
        this.jpaExerciseTypeRepository = jpaExerciseTypeRepository;
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public ExerciseRecordDto exerciseStart(Long uid, Long exno) throws NoSuchObjectException {

        Optional<ExerciseTypeData> exercise = jpaExerciseTypeRepository.findById(exno);
        if (exercise.isEmpty()) {
            throw new NoSuchObjectException("There is NO such exercise_no!!");
        }
        if (exercise.get().getRecType().equals('N')) {
            throw new IllegalArgumentException("This exercise_no is a NUMBER recording type!!");
        }
        Optional<UserData> user = jpaUserRepository.findById(uid);
        if (user.isEmpty()) {
            throw new NoSuchObjectException("There is NO such user_id!!");
        }

        ExerciseRecordDto exerciseRecordDto = new ExerciseRecordDto();

        exerciseRecordDto.setUserId(uid);
        exerciseRecordDto.setExerciseNo(exno);
        exerciseRecordDto.setStartTime(new Timestamp(System.currentTimeMillis()));

        return exerciseRecordDto;
    }

    @Override
    public ExerciseRecordDto exerciseEnd(ExerciseRecordDto exerciseRecordDto) {

        if (!isTimeFormat(exerciseRecordDto.getExTime())) {
            throw new IllegalArgumentException("exerciseTime does NOT match the format!!\n" +
                    "Please try according to the format: \"HH:MM:SS.SS\"");
        }

        exerciseRecordDto.setEndTime(new Timestamp(System.currentTimeMillis()));

        ExerciseRecordData entity = exerciseRecordDto.toEntity();

        jpaExerciseRepository.save(entity);

        return exerciseRecordDto;
    }

    private boolean isTimeFormat(String str) {
        return str.matches("\\d+:\\d{2}:\\d{2}.\\d{2}");
    }

    @Override
    public ExerciseRecordDto exerciseRecord(ExerciseRecordDto exerciseRecordDto) throws NoSuchObjectException {

        Optional<ExerciseTypeData> exercise = jpaExerciseTypeRepository.findById(exerciseRecordDto.getExerciseNo());
        if (exercise.isEmpty()) {
            throw new NoSuchObjectException("There is NO such exercise_no!!");
        }
        if (exercise.get().getRecType().equals('T')) {
            throw new IllegalArgumentException("This exercise_no is a TIME recording type!!");
        }
        Optional<UserData> user = jpaUserRepository.findById(exerciseRecordDto.getUserId());
        if (user.isEmpty()) {
            throw new NoSuchObjectException("There is NO such user_id!!");
        }
        if (exerciseRecordDto.getSets() > exerciseRecordDto.getCount()) {
            throw new IllegalArgumentException("set CANNOT be greater than count!!");
        }

        ExerciseRecordData entity = exerciseRecordDto.toEntity();

        jpaExerciseRepository.save(entity);

        return exerciseRecordDto;
    }

    @Override
    public List<ExerciseRecordDto> getExerciseRecord() {
        List<ExerciseRecordData> dataList = jpaExerciseRepository.findAll();
        List<ExerciseRecordDto> dtoList = new ArrayList<>();

        for (ExerciseRecordData ed : dataList) {
            ExerciseRecordDto dto = new ExerciseRecordDto();

            dto.setId(ed.getId());
            dto.setSets(ed.getSets());
            dto.setCount(ed.getCount());
            dto.setUserId(ed.getUserId());
            dto.setStartTime(ed.getStartTime());
            dto.setEndTime(ed.getEndTime());
            dto.setExTime(ed.getExTime());
            dto.setExerciseNo(ed.getExerciseNo());
            dto.setRegdate(ed.getRegdate());

            dtoList.add(dto);
        }

        return dtoList;
    }

    @Override
    public ExerciseTypeDto addExType(ExerciseTypeDto exerciseTypeDto) throws NoSuchObjectException {

        Optional<UserData> user = jpaUserRepository.findById(exerciseTypeDto.getCBy());
        if (user.isEmpty()) {
            throw new NoSuchObjectException("There is NO such user_id!!");
        }

        ExerciseTypeData entity = exerciseTypeDto.toEntity();

        jpaExerciseTypeRepository.save(entity);

        return exerciseTypeDto;
    }

    @Override
    public List<ExerciseTypeDto> getAllExerciseTypes() {
        List<ExerciseTypeData> dataList = jpaExerciseTypeRepository.findAll();
        ArrayList<ExerciseTypeDto> dtoList = new ArrayList<>();

        for (ExerciseTypeData etd : dataList) {
            ExerciseTypeDto dto = new ExerciseTypeDto();

            dto.setExNo(etd.getExNo());
            dto.setExName(etd.getExName());
            dto.setRecType(etd.getRecType());

            dtoList.add(dto);
        }

        return dtoList;
    }

    @Override
    public void deleteExerciseRecord(Long id) throws NoSuchObjectException {
        Optional<ExerciseRecordData> record = jpaExerciseRepository.findById(id);
        if (record.isEmpty()) {
            throw new NoSuchObjectException("There is NO such record_no!!");
        }

        jpaExerciseRepository.deleteById(id);
    }

    @Override
    public List<ExerciseTypeDto> getExerciseTypesByUserId(Long userId) throws NoSuchObjectException {
        Optional<UserData> record = jpaUserRepository.findById(userId);
        if (record.isEmpty()) {
            throw new NoSuchObjectException("There is NO such user_id!!");
        }

        List<ExerciseTypeData> findList = jpaExerciseTypeRepository.findByUserId(userId);

        ArrayList<ExerciseTypeDto> etdList = new ArrayList<>();

        for (ExerciseTypeData etd : findList) {
            ExerciseTypeDto dto = new ExerciseTypeDto();

            dto.setExNo(etd.getExNo());
            dto.setExName(etd.getExName());
            dto.setRecType(etd.getRecType());
            dto.setCBy(etd.getCBy());

            etdList.add(dto);
        }

        return etdList;
    }
}
