package com.example.HealthFriends.service;

import com.example.HealthFriends.dto.AuthResponseDTO;
import com.example.HealthFriends.entity.User;
import com.example.HealthFriends.jwt.AuthToken;
import com.example.HealthFriends.jwt.AuthTokenProvider;
import com.example.HealthFriends.repository.JPAUserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthTokenProvider authTokenProvider;
    private final JPAUserRepository jpaUserRepository;

    public AuthResponseDTO updateToken(AuthToken authToken) {
        Claims claims = authToken.getTokenClaims();
        if (claims == null) {
            return null;
        }

        String socialId = claims.getSubject();

        AuthToken newAppToken = authTokenProvider.createUserAppToken(Long.valueOf(socialId));

        return AuthResponseDTO.builder()
                .appToken(newAppToken.getToken())
                .build();
    }

    public Long getMemberId(String token) {
        AuthToken authToken = authTokenProvider.convertAuthToken(token);

        Claims claims = authToken.getTokenClaims();
        if (claims == null) {
            return null;
        }

        try {
            User user =  jpaUserRepository.findById_(Long.valueOf(claims.getSubject()));
            return user.getId();

        } catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자가 존재하지 않습니다.");
        }
    }

}
