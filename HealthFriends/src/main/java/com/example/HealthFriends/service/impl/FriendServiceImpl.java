package com.example.HealthFriends.service.impl;

import com.example.HealthFriends.dto.FriendsRequestDTO;
import com.example.HealthFriends.dto.TokenDTO;
import com.example.HealthFriends.entity.FriendState;
import com.example.HealthFriends.entity.Friends;
import com.example.HealthFriends.repository.JPAFriendsReopository;
import com.example.HealthFriends.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService {

    private final JPAFriendsReopository jpaFriendsReopository;

    // 친구 요청
    public Friends addToFriends(FriendsRequestDTO friendsRequestDTO) {

        if (jpaFriendsReopository.findByRootUserAndFriendUser(friendsRequestDTO.getRoot_id(), friendsRequestDTO.getFriend_id()).getAre_friends() == FriendState.DENIED) {
            System.out.println("Can't Add This User");
            return null;
        } else if (jpaFriendsReopository.findByRootUserAndFriendUser(friendsRequestDTO.getRoot_id(), friendsRequestDTO.getFriend_id()).getAre_friends() == FriendState.TRUE) {
            System.out.println("Already Add This User");
            return null;
        } else {
            Friends friends = Friends.builder()
                    .friend_user(friendsRequestDTO.getFriend_id()) // 친구 신청한 유저 id
                    .root_user(friendsRequestDTO.getRoot_id()) // 대상 유저 id
                    .are_friends(FriendState.FALSE) // 초기 친구 신청이라 false로 고정
                    .build();

            jpaFriendsReopository.save(friends);
            return friends;
        }
    }

    // 친구 수락
    public Friends acceptFriend(FriendsRequestDTO friendsRequestDTO) {
        Friends friends = jpaFriendsReopository.findByRootUserAndFriendUser(friendsRequestDTO.getRoot_id(), friendsRequestDTO.getFriend_id());

        if (friends.getAre_friends() == FriendState.FALSE || friends.getAre_friends() == FriendState.DELETED) {
            return jpaFriendsReopository.acceptFriend(friends.getId());
        } else if (friends.getAre_friends() == FriendState.TRUE) {
            System.out.println("Already Friends");
            return null;
        } else {
            System.out.println("Denied User");
            return null;
        }
    }

    // 친구 거절
    public Friends denyFriend(FriendsRequestDTO friendsRequestDTO) {
        Friends friends = jpaFriendsReopository.findByRootUserAndFriendUser(friendsRequestDTO.getRoot_id(), friendsRequestDTO.getFriend_id());

        if (friends.getAre_friends() == FriendState.FALSE) {
            return jpaFriendsReopository.denyFriend(friends.getId());
        } else if (friends.getAre_friends() == FriendState.TRUE) {
            System.out.println("Already Friends");
            return null;
        } else if (friends.getAre_friends() == FriendState.DENIED) {
            System.out.println("Already Denied");
            return null;
        } else {
            return null;
        }
    }

    // 친구 삭제
    public Friends deleteFriend(FriendsRequestDTO friendsRequestDTO) {
        Friends friends = jpaFriendsReopository.findByRootUserAndFriendUser(friendsRequestDTO.getRoot_id(), friendsRequestDTO.getFriend_id());

        if (friends.getAre_friends() == FriendState.FALSE) {
            System.out.println("Not A Friend");
            return null;
        } else if (friends.getAre_friends() == FriendState.TRUE) {
            jpaFriendsReopository.deleteFriend(friends.getId());
            return null;
        } else if (friends.getAre_friends() == FriendState.DENIED) {
            System.out.println("Denied User");
            return null;
        } else {
            System.out.println("Already Deleted");
            return null;
        }
    }

    // 받은 친구 요청 목록
    public List<Friends> getReceivedRequests(TokenDTO tokenDTO) {
        List<Friends> list = jpaFriendsReopository.findReceivedRequestsByRootUser(tokenDTO.getId()).stream().toList();
        if(list.isEmpty()){
            System.out.println("No Requests Received");
            return null;
        } else {
            return list;
        }
    }

    // 보낸 친구 요청 목록
    public List<Friends> getSentRequests(TokenDTO tokenDTO) {
        List<Friends> list = jpaFriendsReopository.findSentRequestsByRootUser(tokenDTO.getId()).stream().toList();
        if(list.isEmpty()){
            System.out.println("No Requests Sent");
            return null;
        } else {
            return list;
        }
    }

    // 친구 목록 조회
    public List<Friends> getFriends(TokenDTO tokenDTO) {
        List<Friends> list = jpaFriendsReopository.findFriendsByRootUser(tokenDTO.getId()).stream().toList();
        if(list.isEmpty()){
            System.out.println("No Friends");
            return null;
        } else {
            return list;
        }
    }
}
