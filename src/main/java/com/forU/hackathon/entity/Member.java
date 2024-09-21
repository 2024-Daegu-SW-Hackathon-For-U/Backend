package com.forU.hackathon.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK

    @Column(nullable = false, unique = true)
    private String nickname; // 닉네임 필드

    // 카카오 ID 추가 (선택 사항)
    @Column(nullable = false, unique = true)
    private String kakaoId; // 카카오 ID 필드

    // 생성자 추가 (nickname과 kakaoId를 인자로 받는 생성자)
    public Member(String nickname, String kakaoId) {
        this.nickname = nickname;
        this.kakaoId = kakaoId;
    }
}
