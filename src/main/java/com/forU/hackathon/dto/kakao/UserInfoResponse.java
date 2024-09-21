package com.forU.hackathon.dto.kakao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoResponse {
    private Long id; // 카카오 ID
    private String nickname; // 닉네임
}