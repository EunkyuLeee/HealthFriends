package com.example.HealthFriends.controller;

import com.example.HealthFriends.service.ExerciseRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ExerciseRecordController {
    private final ExerciseRecordService exerciseRecordSercice;

    @GetMapping("/record")
    public String retrieveRecord(){
        Long id = 1L; //temporary user id
        exerciseRecordSercice.findByUserId(id).forEach(System.out::println);
        return "redirect:/";
    }
}
