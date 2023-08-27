package com.example.HealthFriends.repository;

import com.example.HealthFriends.entity.Friends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface JPAFriendsReopository extends JpaRepository<Friends, Long> {

    @Query(value = "SELECT * FROM friends f WHERE f.friend_user = :friend_id AND f.root_user = :root_id", nativeQuery = true)
    Friends findByRootUserAndFriendUser(Long root_id, Long friend_id);

    @Query(value = "SELECT * FROM friends f WHERE f.root_user = :root_id AND f.are_friends = TRUE", nativeQuery = true)
    Optional<Friends> findFriendsByRootUser(Long root_id);

    @Query(value = "SELECT * FROM friends f WHERE f.root_user = :root_id AND (f.are_friends = FALSE OR f.are_friends = DELETED)", nativeQuery = true)
    Optional<Friends> findReceivedRequestsByRootUser(Long root_id);

    @Query(value = "SELECT * FROM friends f WHERE f.friend_user = :root_id AND (f.are_friends = FALSE OR f.are_friends = DELETED)", nativeQuery = true)
    Optional<Friends> findSentRequestsByRootUser(Long root_id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE friends SET are_friends = TRUE WHERE id = :id", nativeQuery = true)
    Friends acceptFriend(Long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE friends SET are_friends = DENIED WHERE id = :id", nativeQuery = true)
    Friends denyFriend(Long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE friends SET are_friends = DELETED WHERE id = :id", nativeQuery = true)
    Friends deleteFriend(Long id);
}
