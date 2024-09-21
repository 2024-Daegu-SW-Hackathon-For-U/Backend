package com.forU.hackathon.dto.comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponse {

    private Long id; // 코멘트 ID
    private String content; // 코멘트 내용
    private Long memberId; // FK: Member ID
    private Long placeId; // FK: Place ID

    // 기본 생성자
    public CommentResponse() {}

    // 생성자
    public CommentResponse(Long id, String content, Long memberId, Long placeId) {
        this.id = id;
        this.content = content;
        this.memberId = memberId;
        this.placeId = placeId;
    }
}