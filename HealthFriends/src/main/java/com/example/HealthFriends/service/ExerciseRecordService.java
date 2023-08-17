package com.example.HealthFriends.service;

import com.example.HealthFriends.dto.exerciseRecord.ExerciseRecordResponseDTO;
import com.example.HealthFriends.dto.exerciseRecord.ExerciseRecordSaveDTO;
import com.example.HealthFriends.repository.ExerciseRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public ExerciseRecordResponseDTO findByExerciseRecordNo(Long exerciseRecordNo){
        return new ExerciseRecordResponseDTO(exerciseRecordRepository.findByExerciseRecordNo(exerciseRecordNo));
    }

    public List<ExerciseRecordResponseDTO> findByExerciseNo(Long exerciseNo){
        return exerciseRecordRepository.findByExerciseEntity_ExerciseNo(exerciseNo).stream().map(ExerciseRecordResponseDTO::new).collect(java.util.stream.Collectors.toList());
    }

    public List<ExerciseRecordResponseDTO> findByUserId(Long userId){
        return exerciseRecordRepository.findByUserEntity_id(userId).stream().map(ExerciseRecordResponseDTO::new).collect(java.util.stream.Collectors.toList());
    }
}
