package com.forU.hackathon.service;

import com.forU.hackathon.dto.comment.CommentRequest;
import com.forU.hackathon.dto.comment.CommentResponse;
import java.util.List;
import com.forU.hackathon.entity.Comment;
import com.forU.hackathon.entity.Place;
import com.forU.hackathon.entity.Member;
import com.forU.hackathon.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    // 코멘트 등록
    @Transactional
    public CommentResponse createComment(CommentRequest commentRequestDTO, Member member, Place place) {
        // 현재 최대 ID 조회
        Long maxId = commentRepository.findMaxId();
        Long newId = (maxId == null) ? 1 : maxId + 1;

        Comment comment = new Comment();
        comment.setId(newId);
        comment.setContent(commentRequestDTO.getContent());
        comment.setMember(member);
        comment.setPlace(place);
        comment = commentRepository.save(comment);
        return new CommentResponse(comment.getId(), comment.getContent(), member.getId(), place.getId());
    }
    // 코멘트 수정
    @Transactional
    public CommentResponse updateComment(Long id, CommentRequest commentRequestDTO) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            comment.setContent(commentRequestDTO.getContent());
            commentRepository.save(comment);
            return new CommentResponse(comment.getId(), comment.getContent(), comment.getMember().getId(), comment.getPlace().getId());
        }
        return null;
    }

    // 코멘트 조회 - 장소 ID와 멤버 ID로 조회
    @Transactional(readOnly = true)
    public List<CommentResponse> getCommentsByPlaceAndMember(Long placeId, Long memberId) {
        List<Comment> comments = commentRepository.findByPlaceIdAndMemberId(placeId, memberId);
        return comments.stream()
                .map(comment -> new CommentResponse(
                        comment.getId(),
                        comment.getContent(),
                        comment.getMember().getId(),
                        comment.getPlace().getId()
                ))
                .toList(); // 코멘트 리스트 반환
    }

    // 코멘트 삭제
    @Transactional
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    // 동일한 장소에 동일한 회원의 코멘트 존재 여부 확인
    @Transactional(readOnly = true)
    public boolean checkCommentExists(Long placeId, Long memberId) {
        return commentRepository.existsByPlaceIdAndMemberId(placeId, memberId);
    }
}

