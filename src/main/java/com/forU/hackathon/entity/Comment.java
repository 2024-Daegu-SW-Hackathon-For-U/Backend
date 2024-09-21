package com.forU.hackathon.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "comments")
@Getter // 모든 필드에 대한 getter 메서드 생성
@Setter // 모든 필드에 대한 setter 메서드 생성
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK

    @Column(nullable = false)
    private String content; // 코멘트 내용

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Member member; // Member 엔티티와의 관계

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Place place; // Post 엔티티와의 관계

    // 기본 생성자
    public Comment() {}

    // 생성자
    public Comment(String content, Place place, Member member) {
        this.content = content;
        this.place = place;
        this.member = member;
    }
}
