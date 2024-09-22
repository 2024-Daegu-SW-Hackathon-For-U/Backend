package com.forU.hackathon.controller;

import com.forU.hackathon.dto.place.PlaceRequest;
import com.forU.hackathon.dto.place.PlaceResponse;
import com.forU.hackathon.service.PlaceService;
import com.forU.hackathon.service.MapService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "2. Place", description = "장소 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/map/{mapId}/place")
public class PlaceController {
    private final PlaceService placeService;
    private final MapService mapService; // MapService 필드 추가

    @PostMapping()
    @Operation(summary = "특정 지도에 장소를 추가합니다.")
    public ResponseEntity<Void> createPlace(@PathVariable Long mapId, @RequestBody PlaceRequest.Create request) {
        placeService.create(mapId, request);
        mapService.incrementCount(mapId); // count 증가
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{placeId}")
    @Operation(summary = "특정 지도의 장소를 삭제합니다.")
    public ResponseEntity<Void> deletePlace(@PathVariable Long mapId, @PathVariable Long placeId) {
        placeService.delete(mapId, placeId);
        mapService.decrementCount(mapId); // count 감소
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping()
    @Operation(summary = "특정 지도의 장소 조회 API")
    public ResponseEntity<List<PlaceResponse.Info>> getPlaces(@PathVariable Long mapId) {
        return new ResponseEntity<>(placeService.getAll(mapId), HttpStatus.OK);
    }
}
