package com.forU.hackathon.service;

import com.forU.hackathon.dto.place.PlaceRequest;
import com.forU.hackathon.dto.place.PlaceResponse;
import com.forU.hackathon.entity.Map;
import com.forU.hackathon.entity.Member;
import com.forU.hackathon.entity.Place;
import com.forU.hackathon.entity.PlaceInMap;
import com.forU.hackathon.repository.MapRepository;
import com.forU.hackathon.repository.PlaceInMapRepository;
import com.forU.hackathon.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlaceService {
    private final MapRepository mapRepository;
    private final PlaceRepository placeRepository;
    private final PlaceInMapRepository placeInMapRepository;

    @Transactional
    public void create(Long mapId, PlaceRequest.Create request) {
        Map map = mapRepository.findById(mapId)
                .orElseThrow(() -> new NoSuchElementException("해당 지도가 존재하지 않습니다."));

        Place place = placeRepository.findById(request.getPlaceId()).orElse(
                new Place(request.getPlaceId(), request.getName(), request.getAddress(), request.getLatitude(), request.getLongitude(), null)
        );

        placeRepository.save(place);

        if (placeInMapRepository.existsByMapAndPlace(map, place)) {
            throw new IllegalStateException("이미 추가되어있는 장소입니다.");
        }
        PlaceInMap placeInMap = new PlaceInMap(null, map, place);
        placeInMapRepository.save(placeInMap);
    }

    @Transactional
    public void delete(Long mapId, Long placeId) {
        placeInMapRepository.deleteByMapIdAndPlaceId(mapId, placeId);
    }

    @Transactional(readOnly = true)
    public List<PlaceResponse.Info> getAll(Long mapId) {
        //ToDo: 쿼리 최적화 필요
        List<Place> places = placeInMapRepository.findAllByMapId(mapId)
                .stream()
                .map(PlaceInMap::getPlace)
                .toList();
        return places.stream().map(PlaceResponse.Info::from).toList();
    }

    // findById 메서드 추가
    public Optional<Place> findById(Long id) {
        return placeRepository.findById(id);
    }
}
