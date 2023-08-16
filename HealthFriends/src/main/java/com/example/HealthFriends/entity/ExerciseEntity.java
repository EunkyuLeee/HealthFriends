package com.example.HealthFriends.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "tbl_exercise")
public class ExerciseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exerciseNo;

    private String exerciseName;

    public void update(String exerciseName) {
        this.exerciseName = exerciseName;
    }
}
