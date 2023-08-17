package com.example.HealthFriends.service.impl;

import com.example.HealthFriends.dto.ExerciseDto;
import com.example.HealthFriends.entity.ExerciseData;
import com.example.HealthFriends.repository.JPAExerciseRepository;
import com.example.HealthFriends.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final JPAExerciseRepository jpaExerciseRepository;

    @Autowired
    public ExerciseServiceImpl(JPAExerciseRepository jpaExerciseRepository) {
        this.jpaExerciseRepository = jpaExerciseRepository;
    }

    @Override
    public ExerciseDto exerciseStart(Long uid, Long exno) {
        ExerciseDto exerciseDto = new ExerciseDto();

        exerciseDto.setUserId(uid);
        exerciseDto.setExerciseNo(exno);
        exerciseDto.setStartTime(new Timestamp(System.currentTimeMillis()));

        return exerciseDto;
    }

    @Override
    public ExerciseDto exerciseEnd(ExerciseDto exerciseDto) {

        exerciseDto.setEndTime(new Timestamp(System.currentTimeMillis()));

        ExerciseData entity = exerciseDto.toTimeEntity();

        System.out.println(exerciseDto.toString());
        System.out.println(entity.toString());;

        jpaExerciseRepository.save(entity);

        return exerciseDto;
    }

    @Override
    public ExerciseDto exerciseRecord(ExerciseDto exerciseDto) {

        ExerciseData entity = exerciseDto.toCountEntity();

        System.out.println(exerciseDto.toString());
        System.out.println(entity.toString());

        jpaExerciseRepository.save(entity);

        return exerciseDto;
    }

    @Override
    public List<ExerciseDto> exerciseGet() {
        List<ExerciseData> dataList = jpaExerciseRepository.findAll();
        List<ExerciseDto> dtoList = new ArrayList<>();

        for (ExerciseData ed : dataList) {
            ExerciseDto dto = new ExerciseDto();

            dto.setId(ed.getId());
            dto.setSets(ed.getSets());
            dto.setCount(ed.getCount());
            dto.setUserId(ed.getUserId());
            dto.setStartTime(ed.getStartTime());
            dto.setEndTime(ed.getEndTime());
            dto.setExerciseNo(ed.getExerciseNo());
            dto.setRegdate(ed.getRegdate());

            dtoList.add(dto);
        }

        return dtoList;
    }
}
