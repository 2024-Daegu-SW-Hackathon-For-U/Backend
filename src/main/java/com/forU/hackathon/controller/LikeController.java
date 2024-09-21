package com.forU.hackathon.controller;

import com.forU.hackathon.service.LikeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="5. Like", description = "좋아요 관련 API")
@RestController
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @PostMapping("/api/like/place/{placeId}")
    public ResponseEntity<Void> createLikePlace(@PathVariable("placeId") Long placeId) {

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
