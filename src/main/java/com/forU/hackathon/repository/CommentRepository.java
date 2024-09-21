package com.forU.hackathon.repository;

import com.forU.hackathon.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 필요한 쿼리 메서드 추가 가능
}
