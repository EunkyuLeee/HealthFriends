package com.example.HealthFriends.dto;

import com.example.HealthFriends.entity.ExerciseScheduleData;
import lombok.Data;

@Data
public class ExerciseScheduleDto {
    private Long id;
    private String title;
    private Integer year;
    private Integer month;
    private Integer day;
    private String start_time;
    private String end_time;
    private Boolean complete;
    private Long user_id;

    public ExerciseScheduleData toEntity() {
        return ExerciseScheduleData.builder()
                .id(id)
                .title(title)
                .year(year)
                .month(month)
                .day(day)
                .sTime(start_time)
                .eTime(end_time)
                .complete(complete)
                .uid(user_id)
                .build();
    }
}
