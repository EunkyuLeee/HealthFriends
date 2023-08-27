package com.example.HealthFriends.service;

import com.example.HealthFriends.client.ClientKakao;
import com.example.HealthFriends.dto.AuthRequestDTO;
import com.example.HealthFriends.dto.AuthResponseDTO;
import com.example.HealthFriends.entity.User;
import com.example.HealthFriends.jwt.AuthToken;
import com.example.HealthFriends.jwt.AuthTokenProvider;
import com.example.HealthFriends.repository.JPAUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class KakaoAuthService {

    private final JPAUserRepository jpaUserRepository;
    private final ClientKakao clientKakao;
    private final AuthTokenProvider authTokenProvider;

    @Transactional
    public AuthResponseDTO login(AuthRequestDTO authRequest) throws IOException {
        User kakaoUser = clientKakao.getUserData(authRequest.getAccessToken());
        Long id = kakaoUser.getId();
        User user = jpaUserRepository.findById_(id);

        AuthToken authToken = authTokenProvider.createUserAppToken(id);

        if (user == null) {
            jpaUserRepository.save(kakaoUser);
        }

        return AuthResponseDTO.builder()
                .appToken(authToken.getToken())
                .build();
    }

}
