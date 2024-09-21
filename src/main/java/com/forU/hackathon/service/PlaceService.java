package com.forU.hackathon.service;

import com.forU.hackathon.dto.place.PlaceRequest;
import com.forU.hackathon.entity.Map;
import com.forU.hackathon.entity.Place;
import com.forU.hackathon.entity.PlaceInMap;
import com.forU.hackathon.repository.MapRepository;
import com.forU.hackathon.repository.PlaceInMapRepository;
import com.forU.hackathon.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PlaceService {
    private final MapRepository mapRepository;
    private final PlaceRepository placeRepository;
    private final PlaceInMapRepository placeInMapRepository;

    @Transactional
    public void create(PlaceRequest.Create request) {
        Map map = mapRepository.findById(request.getMapId())
                .orElseThrow(() -> new NoSuchElementException("해당 지도가 존재하지 않습니다."));

        Place place = placeRepository.findById(request.getPlaceId()).orElse(
                new Place(request.getPlaceId(), request.getName(), request.getAddress(), request.getLatitude(), request.getLongitude(), null)
        );

        placeRepository.save(place);

        PlaceInMap placeInMap = new PlaceInMap(null, map, place);
        placeInMapRepository.save(placeInMap);
    }
}
