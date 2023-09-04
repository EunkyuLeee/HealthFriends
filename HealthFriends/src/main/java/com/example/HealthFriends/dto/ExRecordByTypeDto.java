package com.example.HealthFriends.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ExRecordByTypeDto {
    Long id;
    String title;
    Timestamp start_time;
    String exercise_time;
    Integer sets;
    Integer count;
}
