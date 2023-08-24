package com.example.HealthFriends.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "exercise_schedule")
public class ExerciseScheduleData {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "year")
    private int year;

    @Column(name = "month")
    private int month;

    @Column(name = "day")
    private int day;

    @Column(name = "start_time")
    private String sTime;

    @Column(name = "end_time")
    private String eTime;

    @Column(name = "complete")
    private Character complete;

    @Column(name = "user_id")
    private Long uid;
}
