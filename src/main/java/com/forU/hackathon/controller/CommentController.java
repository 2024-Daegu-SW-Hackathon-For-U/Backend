package com.forU.hackathon.controller;

import com.forU.hackathon.dto.comment.CommentRequest;
import com.forU.hackathon.dto.comment.CommentResponse;
import com.forU.hackathon.entity.Member;
import com.forU.hackathon.entity.Place;
import com.forU.hackathon.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 코멘트 등록
    @PostMapping
    public ResponseEntity<CommentResponse> createComment(@RequestBody CommentRequest commentRequestDTO) {
        // Member와 Place는 실제로 DB에서 조회해야 함
        Member member = new Member(); // 실제 Member 객체로 교체
        member.setId(commentRequestDTO.getMemberId());

        Place place = new Place(); // 실제 Place 객체로 교체
        place.setId(commentRequestDTO.getPlaceId());

        CommentResponse createdComment = commentService.createComment(commentRequestDTO, member, place);
        return ResponseEntity.ok(createdComment);
    }

    // 코멘트 수정
    @PutMapping("/{id}")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable Long id, @RequestBody CommentRequest commentRequestDTO) {
        CommentResponse updatedComment = commentService.updateComment(id, commentRequestDTO);
        if (updatedComment != null) {
            return ResponseEntity.ok(updatedComment);
        }
        return ResponseEntity.notFound().build(); // 예외 처리 필요
    }

    // 코멘트 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}
