package com.example.HealthFriends.service;

import com.example.HealthFriends.dto.FriendsRequestDTO;
import com.example.HealthFriends.dto.TokenDTO;
import com.example.HealthFriends.entity.Friends;

import java.util.List;

public interface FriendService {
    Friends addToFriends(FriendsRequestDTO friendsRequestDTO);
    Friends acceptFriend(FriendsRequestDTO friendsRequestDTO);
    Friends denyFriend(FriendsRequestDTO friendsRequestDTO);
    Friends deleteFriend(FriendsRequestDTO friendsRequestDTO);
    List<Friends> getReceivedRequests(TokenDTO tokenDTO);
    List<Friends> getSentRequests(TokenDTO tokenDTO);
    List<Friends> getFriends(TokenDTO tokenDTO);
}
