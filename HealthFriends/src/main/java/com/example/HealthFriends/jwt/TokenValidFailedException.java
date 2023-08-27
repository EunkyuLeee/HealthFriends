package com.example.HealthFriends.jwt;

public class TokenValidFailedException extends RuntimeException{

    public TokenValidFailedException() {
        super("Failed to generate Token.");
    }

    public TokenValidFailedException(String message) {
        super(message);
    }

}
