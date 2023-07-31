package com.example.HealthFriends.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OAuth2UserController {

    @GetMapping("/login")
    @ResponseBody
    public String login() {
        return "<a href=\"/oauth2/authorization/google\">구글 로그인</a>" +
                "<a href=\"/oauth2/authorization/kakao\">카카오 로그인</a>" +
                "<a href=\"/oauth2/authorization/naver\">네이버 로그인</a>";
    }

    @GetMapping("/login/success")
    public String loginSuccess() {
        return "loginSuccess";
    }

}
