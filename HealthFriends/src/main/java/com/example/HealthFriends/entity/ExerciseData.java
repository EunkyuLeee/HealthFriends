package com.example.HealthFriends.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Data
@Table(name = "tbl_exercise_record")
public class ExerciseData {

    @Id
    @Column(name = "exercise_record_no")
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @Column(name = "count_exercise")
    private Integer count;

    @Column(name = "sets_exercise")
    private Integer sets;

    @Column(name = "start_exercise_time")
    private Timestamp startTime;

    @Column(name = "end_exercise_time")
    private Timestamp endTime;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "exercise_no")
    private Long exerciseNo;

    @Column(name = "regdate")
    private Timestamp regdate;
}
