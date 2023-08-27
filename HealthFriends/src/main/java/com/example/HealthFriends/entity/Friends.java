package com.example.HealthFriends.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "friends")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Friends {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "root_user")
    private Long root_user;

    @Column(name = "friend_user")
    private Long friend_user;

    @Enumerated(EnumType.STRING)
    @Column(name = "are_friends")
    private FriendState are_friends;

}
