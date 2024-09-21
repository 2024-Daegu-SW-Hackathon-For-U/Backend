package com.forU.hackathon.repository;

import com.forU.hackathon.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT MAX(c.id) FROM Comment c")
    Long findMaxId();
}
