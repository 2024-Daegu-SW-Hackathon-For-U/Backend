package com.forU.hackathon.controller;

import com.forU.hackathon.dto.map.MapRequest;
import com.forU.hackathon.dto.map.MapResponse;
import com.forU.hackathon.service.MapService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name= "1. Map", description = "지도 관련 API")
@RestController
@RequiredArgsConstructor
public class MapController {
    private final MapService mapService;

    @PostMapping("/api/map")
    @Operation(summary = "지도 생성 API")
    public ResponseEntity<MapResponse.Info> createMap(@Valid @RequestBody MapRequest.Create request) {
        return new ResponseEntity<>(mapService.create(request), HttpStatus.CREATED);
    }
}
