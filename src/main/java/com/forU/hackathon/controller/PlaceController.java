package com.forU.hackathon.controller;

import com.forU.hackathon.dto.place.PlaceRequest;
import com.forU.hackathon.dto.place.PlaceResponse;
import com.forU.hackathon.service.PlaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @DeleteMapping("/api/{mapId}/{placeId}")
    @Operation(summary = "특정 지도의 장소를 삭제합니다.")
    public ResponseEntity<Void> deletePlace(@PathVariable Long mapId, @PathVariable Long placeId) {
        placeService.delete(mapId, placeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/api/map/{mapId}/place")
    @Operation(summary = "특정 지도의 장소 조회 API")
    public ResponseEntity<List<PlaceResponse.Info>> getPlaces(@PathVariable Long mapId) {
        return new ResponseEntity<>(placeService.getAll(mapId), HttpStatus.OK);
    }
}
