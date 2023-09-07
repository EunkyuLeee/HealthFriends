package com.example.HealthFriends.entity;

import java.sql.Timestamp;

public interface ExDailyRecord {
    Long getId();

    Timestamp getStart_time();

    String getExercise_time();

    Integer getSets();

    Integer getCount();

    String getExercise_name();
}
