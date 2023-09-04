package com.example.HealthFriends.client;

import com.example.HealthFriends.entity.User;

import java.io.IOException;

public interface ClientProxy {
    User getUserData(String accessToken) throws IOException;
}
