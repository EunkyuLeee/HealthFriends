package com.example.HealthFriends.service;

import com.example.HealthFriends.dto.exerciseRecord.ExerciseRecordResponseDTO;
import com.example.HealthFriends.dto.exerciseRecord.ExerciseRecordSaveDTO;
import com.example.HealthFriends.repository.ExerciseRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseRecordService {
    private final ExerciseRecordRepository exerciseRecordRepository;

    public Long save(ExerciseRecordSaveDTO dto){
        Long exerciseRecordNo = exerciseRecordRepository.save(dto.toEntity()).getExerciseRecordNo();
        return exerciseRecordNo;
    }

    public List<ExerciseRecordResponseDTO> findAll(){
        return exerciseRecordRepository.findAll().stream().map(ExerciseRecordResponseDTO::new).collect(java.util.stream.Collectors.toList());
    }
}
