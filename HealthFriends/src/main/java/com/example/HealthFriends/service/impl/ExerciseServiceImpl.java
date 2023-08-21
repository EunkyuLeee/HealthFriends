package com.example.HealthFriends.service.impl;

import com.example.HealthFriends.dto.ExerciseRecordDto;
import com.example.HealthFriends.dto.ExerciseTypeDto;
import com.example.HealthFriends.entity.ExerciseRecordData;
import com.example.HealthFriends.entity.ExerciseTypeData;
import com.example.HealthFriends.repository.JPAExerciseRepository;
import com.example.HealthFriends.repository.JPAExerciseTypeRepository;
import com.example.HealthFriends.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final JPAExerciseRepository jpaExerciseRepository;
    private final JPAExerciseTypeRepository jpaExerciseTypeRepository;

    @Autowired
    public ExerciseServiceImpl(JPAExerciseRepository jpaExerciseRepository, JPAExerciseTypeRepository jpaExerciseTypeRepository) {
        this.jpaExerciseRepository = jpaExerciseRepository;
        this.jpaExerciseTypeRepository = jpaExerciseTypeRepository;
    }

    @Override
    public ExerciseRecordDto exerciseStart(Long uid, Long exno) {
        ExerciseRecordDto exerciseRecordDto = new ExerciseRecordDto();

        exerciseRecordDto.setUserId(uid);
        exerciseRecordDto.setExerciseNo(exno);
        exerciseRecordDto.setStartTime(new Timestamp(System.currentTimeMillis()));

        return exerciseRecordDto;
    }

    @Override
    public ExerciseRecordDto exerciseEnd(ExerciseRecordDto exerciseRecordDto) {

        exerciseRecordDto.setEndTime(new Timestamp(System.currentTimeMillis()));

        ExerciseRecordData entity = exerciseRecordDto.toEntity();

        jpaExerciseRepository.save(entity);

        return exerciseRecordDto;
    }

    @Override
    public ExerciseRecordDto exerciseRecord(ExerciseRecordDto exerciseRecordDto) {

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
            dto.setExerciseNo(ed.getExerciseNo());
            dto.setRegdate(ed.getRegdate());

            dtoList.add(dto);
        }

        return dtoList;
    }

    @Override
    public ExerciseTypeDto addExType(ExerciseTypeDto exerciseTypeDto) {

        ExerciseTypeData entity = exerciseTypeDto.toEntity();

        jpaExerciseTypeRepository.save(entity);

        return exerciseTypeDto;
    }

    @Override
    public List<ExerciseTypeDto> getExerciseType() {
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
    public List<String> sortedListByTime(Long exNo){
        List<ExerciseRecordData> entities = jpaExerciseRepository.findByExerciseNo(exNo);
        List<ExerciseRecordDto> dtos = new ArrayList<>();

        for(ExerciseRecordData ed : entities){
            ExerciseRecordDto dto = new ExerciseRecordDto();
            dto.setExerciseTime(ed.getExerciseTime());
            dtos.add(dto);
        }

        PriorityQueue<String> heap = new PriorityQueue<>(Collections.reverseOrder());

        for(int i = 0; i < dtos.size(); i++){
            if(dtos.get(i).getExerciseTime() != null){
                heap.add(dtos.get(i).getExerciseTime());
            }
        }
        for(int i = 0; i < dtos.size(); i++){
            dtos.get(i).setExerciseTime(heap.poll());
        }

        List<String> sortedList = dtos.stream().map(ExerciseRecordDto::getExerciseTime).collect(Collectors.toList());

        return sortedList;

    }
}
