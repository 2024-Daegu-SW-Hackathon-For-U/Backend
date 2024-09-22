package com.forU.hackathon.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    private Long id; // PK

    @Column(nullable = false, unique = false)
    private String nickname; // 닉네임 필드

    // 생성자 추가 (nickname과 kakaoId를 인자로 받는 생성자)
    public Member(String nickname, Long Id) {
        this.nickname = nickname;
        this.id = Id;
    }

    // Member와 Comment 간의 1:N 관계 설정

    // Member와 Map 간의 1:N 관계 설정


    // Member와 Like 간의 1:N 관계 설정


}
