package com.example.HealthFriends.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "exercise_record")
public class ExerciseRecord {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "count")
    private Integer count;

    @Column(name = "sets")
    private Integer sets;

    @Column(name = "start_time")
    private Timestamp startTime;

    @Column(name = "exercise_time")
    private String exTime;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "exercise_no")
    private Long exerciseNo;
}
