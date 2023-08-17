package com.example.HealthFriends.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "exercise_record")
public class ExerciseData {

    @Id
    @Column(name = "record_no")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "count")
    private Integer count;

    @Column(name = "sets")
    private Integer sets;

    @Column(name = "start_time")
    private Timestamp startTime;

    @Column(name = "end_time")
    private Timestamp endTime;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "exercise_no")
    private Long exerciseNo;

    @Column(name = "regdate")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Timestamp regdate;

    @Override
    public String toString() {
        return "ExerciseData{" +
                "id=" + id +
                ", count=" + count +
                ", sets=" + sets +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", userId=" + userId +
                ", exerciseNo=" + exerciseNo +
                ", regdate=" + regdate +
                '}';
    }
}
