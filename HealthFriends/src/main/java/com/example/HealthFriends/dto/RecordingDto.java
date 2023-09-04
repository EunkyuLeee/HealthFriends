package com.example.HealthFriends.dto;

import lombok.Data;

@Data
public class RecordingDto {
    private Long id;
    private String exercise_time;
    private Integer count;
    private Integer sets;
}
