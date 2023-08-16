package com.example.HealthFriends.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.Date;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Table(name = "tbl_exercise_record")
public class ExerciseRecordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exerciseRecordNo;

    @Column(nullable = true)
    private Date startExerciseTime;

    @Column(nullable = true)
    private Date endExerciseTime;

    @Column(nullable = true)
    private Integer setsExercise;

    @Column(nullable = true)
    private Integer countExercise;

    @Column(nullable = true)
    private Date regdate;

    @ManyToOne
    @JoinColumn(name = "exerciseNo")
    private ExerciseEntity exerciseEntity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userEntity;


}
