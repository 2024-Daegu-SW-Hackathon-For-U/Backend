package com.forU.hackathon.controller;

import com.forU.hackathon.dto.comment.CommentRequest;
import com.forU.hackathon.dto.comment.CommentResponse;
import com.forU.hackathon.dto.kakao.UserInfoResponse;
import com.forU.hackathon.entity.Member;
import com.forU.hackathon.entity.Place;
import com.forU.hackathon.service.CommentService;
import com.forU.hackathon.service.KakaoService;
import com.forU.hackathon.service.MemberService;
import com.forU.hackathon.service.PlaceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name= "4. Comment", description = "코멘트 관련 API")
@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;
    private final MemberService memberService;
    private final PlaceService placeService;
    private final KakaoService kakaoService;

    public CommentController(CommentService commentService, MemberService memberService, PlaceService placeService, KakaoService kakaoService) {
        this.commentService = commentService;
        this.memberService = memberService;
        this.placeService = placeService;
        this.kakaoService = kakaoService;
    }
    // 코멘트 등록
    @PostMapping
    public ResponseEntity<CommentResponse> createComment(@RequestBody CommentRequest commentRequestDTO, HttpSession session) {
        String accessToken = (String) session.getAttribute("accessToken");

        // 카카오 API를 통해 사용자 정보 가져오기
        UserInfoResponse userInfo = kakaoService.getUserInfo(accessToken);

        // 사용자 ID를 가져옵니다.
        Long memberId = userInfo.getId();

        // 멤버를 DB에서 조회
        Member member = memberService.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));

        // Request DTO에서 장소 ID를 가져옵니다.
        Long placeId = commentRequestDTO.getPlaceId();
        Place place = placeService.findById(placeId) // 장소를 DB에서 조회
                .orElseThrow(() -> new IllegalArgumentException("장소가 존재하지 않습니다."));

        // 동일한 장소에 동일한 회원이 작성한 코멘트가 있는지 확인
        boolean exists = commentService.checkCommentExists(placeId, memberId);
        if (exists) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null); // 409 Conflict 응답
        }

        // 댓글 생성
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
        return ResponseEntity.notFound().build(); // 예외 처리
    }

    // 코멘트 조회 (GET) - 장소 ID와 멤버 ID로 조회
    @GetMapping("/place/{placeId}/member/{memberId}")
    public ResponseEntity<List<CommentResponse>> getCommentsByPlaceAndMember(
            @PathVariable Long placeId,
            @PathVariable Long memberId) {

        List<CommentResponse> comments = commentService.getCommentsByPlaceAndMember(placeId, memberId);

        if (comments != null && !comments.isEmpty()) {
            return ResponseEntity.ok(comments);
        }
        return ResponseEntity.notFound().build(); // 코멘트가 없을 경우 404 반환
    }

    // 코멘트 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}
