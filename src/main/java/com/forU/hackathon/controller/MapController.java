package com.forU.hackathon.controller;

import com.forU.hackathon.dto.map.MapRequest;
import com.forU.hackathon.dto.map.MapResponse;
import com.forU.hackathon.entity.MapType;
import com.forU.hackathon.entity.Member;
import com.forU.hackathon.service.MapService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name= "1. Map", description = "지도 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/map")
public class MapController {
    private final MapService mapService;

    @PostMapping
    @Operation(summary = "지도 생성 API")
    public ResponseEntity<MapResponse.Info> createMap(@Valid @RequestBody MapRequest.Create request) {
        return new ResponseEntity<>(mapService.create(request), HttpStatus.CREATED);
    }

    @DeleteMapping("/{mapId}")
    @Operation(summary = "지도 삭제 API")
    public ResponseEntity<Void> deleteMap(@PathVariable Long mapId) {
        mapService.delete(mapId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "지도 조회 API")
    public ResponseEntity<List<MapResponse.Info>> getAllMap(@RequestParam(required = false) MapType type) {
        return new ResponseEntity<>(mapService.getAll(type), HttpStatus.OK);
    }

    @GetMapping("/{mapId}")
    @Operation(summary = "특정 지도 조회 API")
    public ResponseEntity<MapResponse.Info> getMap(@PathVariable Long mapId) {
        MapResponse.Info mapInfo = mapService.getMapById(mapId);
        return new ResponseEntity<>(mapInfo, HttpStatus.OK);
    }
}
