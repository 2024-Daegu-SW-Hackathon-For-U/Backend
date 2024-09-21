package com.forU.hackathon.dto.comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequest {

    private String content; // 코멘트 내용
    private Long memberId; // FK: Member ID
    private Long placeId; // FK: Place ID

    // 기본 생성자
    public CommentRequest() {}

    // 생성자
    public CommentRequest(String content, Long memberId, Long placeId) {
        this.content = content;
        this.memberId = memberId;
        this.placeId = placeId;
    }
}