package com.example.HealthFriends.controller;

import com.example.HealthFriends.dto.FriendsRequestDTO;
import com.example.HealthFriends.dto.TokenDTO;
import com.example.HealthFriends.entity.Friends;
import com.example.HealthFriends.service.FriendService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Friend", description = "친구 관련 API")
@RestController
@RequestMapping(value = "/api/friends")
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;

    @Operation(summary = "add to friends", description = "친구 추가")
    @ApiResponse(responseCode = "200", description = "성공")
    @PostMapping("/addToFriends")
    public Friends addToFriends(@RequestBody FriendsRequestDTO friendsRequestDTO) {
        return friendService.addToFriends(friendsRequestDTO);
    }

    @Operation(summary = "accept friends", description = "친구 수락")
    @ApiResponse(responseCode = "200", description = "성공")
    @PutMapping("/acceptFriends")
    public Friends acceptFriends(@RequestBody FriendsRequestDTO friendsRequestDTO) {
        return friendService.acceptFriend(friendsRequestDTO);
    }

    @Operation(summary = "deny friends", description = "친구 거절")
    @ApiResponse(responseCode = "200", description = "성공")
    @PutMapping("/denyFriends")
    public Friends denyFriends(@RequestBody FriendsRequestDTO friendsRequestDTO) {
        return friendService.denyFriend(friendsRequestDTO);
    }

    @Operation(summary = "delete friends", description = "친구 삭제")
    @ApiResponse(responseCode = "200", description = "성공")
    @PutMapping("/deleteFriends")
    public Friends deleteFriends(@RequestBody FriendsRequestDTO friendsRequestDTO) {
        return friendService.deleteFriend(friendsRequestDTO);
    }

    @Operation(summary = "get received requests", description = "받은 친구 요청 목록")
    @ApiResponse(responseCode = "200", description = "성공")
    @PostMapping("/getReceivedRequests")
    public List<Friends> getReceivedRequests(@RequestBody TokenDTO tokenDTO) {
        return friendService.getReceivedRequests(tokenDTO);
    }

    @Operation(summary = "get sent requests", description = "보낸 친구 요청 목록")
    @ApiResponse(responseCode = "200", description = "성공")
    @PostMapping("/getSentRequests")
    public List<Friends> getSentRequests(@RequestBody TokenDTO tokenDTO) {
        return friendService.getSentRequests(tokenDTO);
    }

    @Operation(summary = "get friends", description = "친구 목록")
    @ApiResponse(responseCode = "200", description = "성공")
    @PostMapping("/getFriends")
    public List<Friends> getFriends(@RequestBody TokenDTO tokenDTO) {
        return friendService.getFriends(tokenDTO);
    }
}
