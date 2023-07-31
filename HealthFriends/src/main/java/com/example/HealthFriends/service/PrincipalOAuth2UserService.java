package com.example.HealthFriends.service;

import com.example.HealthFriends.entity.User;
import com.example.HealthFriends.oauth2.*;
import com.example.HealthFriends.repository.JPAUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@Transactional
public class PrincipalOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private JPAUserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        System.out.println("getClientRegistration: "+userRequest.getClientRegistration());
        System.out.println("getAccessToken: "+userRequest.getAccessToken().getTokenValue());
        System.out.println("getAttributes: "+ super.loadUser(userRequest).getAttributes());

        OAuth2User oAuth2User = super.loadUser(userRequest);
        OAuth2UserInfo oAuth2UserInfo = null;

        if(userRequest.getClientRegistration().getRegistrationId().equals("google")) {
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        }
        else if(userRequest.getClientRegistration().getRegistrationId().equals("naver")) {
            oAuth2UserInfo = new NaverUserInfo((Map)oAuth2User.getAttributes().get("response"));
        }
        else if(userRequest.getClientRegistration().getRegistrationId().equals("kakao")) {
            oAuth2UserInfo = new KakaoUserInfo((Map)oAuth2User.getAttributes().get("kakao_account"), String.valueOf(oAuth2User.getAttributes().get("id")));
        }
        else {
            System.out.println("지원하지 않는 서비스");
        }

        String provider = oAuth2UserInfo.getProvider();
        String providerId = oAuth2UserInfo.getProviderId();
        String username = provider + "_" + providerId;
        String password = "TEST";
//        String password = bCryptPasswordEncoder.encode("테스트");
        String email = oAuth2UserInfo.getEmail();
        User userEntity = userRepository.findByName(username);
        if(userEntity == null){
            LocalDateTime create_date = LocalDateTime.now();
            userEntity = User.builder()
                    .name(username)
                    .password(password)
                    .email(email)
                    .provider(provider)
                    .provider_id(providerId)
                    .createDate(create_date)
                    .build();
            System.out.println("Build");
            userRepository.save(userEntity);
        }

        return new PrincipalDetails(userEntity, oAuth2User.getAttributes());
    }

}
