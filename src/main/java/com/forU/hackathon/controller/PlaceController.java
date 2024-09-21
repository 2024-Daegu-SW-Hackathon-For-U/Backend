package com.forU.hackathon.controller;

import com.forU.hackathon.dto.place.PlaceRequest;
import com.forU.hackathon.service.PlaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="2. Place", description = "장소 관련 API")
@RestController
@RequiredArgsConstructor
public class PlaceController {
    private final PlaceService placeService;

    @PostMapping("/api/place")
    @Operation(summary = "특정 지도에 장소를 추가합니다.")
    public ResponseEntity<Void> createPlace(@RequestBody PlaceRequest.Create request) {
        placeService.create(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
