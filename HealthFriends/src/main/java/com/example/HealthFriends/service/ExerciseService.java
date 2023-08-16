package com.example.HealthFriends.service;

import com.example.HealthFriends.dto.exercise.ExerciseResponseDTO;
import com.example.HealthFriends.dto.exercise.ExerciseSaveDTO;
import com.example.HealthFriends.dto.exercise.ExerciseUpdateDTO;
import com.example.HealthFriends.entity.ExerciseEntity;
import com.example.HealthFriends.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

//    @Transactional
    public Long save(ExerciseSaveDTO dto) {
        Long exerciseNo = exerciseRepository.save(dto.toEntity()).getExerciseNo();
        return exerciseNo;
    }

    public List<ExerciseSaveDTO> findAll() {
        return exerciseRepository.findAll().stream().map(ExerciseSaveDTO::new).collect(Collectors.toList());
    }

    public ExerciseResponseDTO findById(Long exerciseNo) {
        return new ExerciseResponseDTO(exerciseRepository.findById(exerciseNo).orElseThrow(() -> new IllegalArgumentException("해당하는 운동이 없습니다.")));
    }

    // 운동 이름으로 수정 - 보류
//    public void updateExercise(Long exerciseNo, ExerciseUpdateDTO dto) {
//        ExerciseEntity entity = exerciseRepository.findById(exerciseNo)
//                .orElseThrow(() -> new IllegalArgumentException("해당하는 운동이 없습니다."));
//        entity.update(dto.getExerciseName());
//    }

    public void delete(Long exerciseNo) {
        exerciseRepository.deleteById(exerciseNo);
    }
}
