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
@Table(name = "tbl_exercise")
public class ExerciseTypeData {
    @Id
    @Column(name = "exercise_no")
    @GeneratedValue(strategy = IDENTITY)
    private Long exNo;

    @Column(name = "exercise_name")
    private String exName;

    @Column(name = "recording_type")
    private Character recType;

    @Column(name = "created_by")
    private Long cBy;
}
