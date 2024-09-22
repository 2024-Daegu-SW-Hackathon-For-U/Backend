package com.forU.hackathon.dto.login;

import com.forU.hackathon.entity.Member;

public class LoginResponse {
    private Member member;
    private String accessToken;

    public LoginResponse(Member member, String accessToken) {
        this.member = member;
        this.accessToken = accessToken;
    }

    public Member getMember() {
        return member;
    }

    public String getAccessToken() {
        return accessToken;
    }
}