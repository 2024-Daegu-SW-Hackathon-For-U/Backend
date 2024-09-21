package com.forU.hackathon.repository;

import com.forU.hackathon.entity.Map;
import com.forU.hackathon.entity.Place;
import com.forU.hackathon.entity.PlaceInMap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlaceInMapRepository extends JpaRepository<PlaceInMap, Long> {
    boolean existsByMapAndPlace(Map map, Place place);

    void deleteByMapIdAndPlaceId(Long mapId, Long placeId);

    List<PlaceInMap> findAllByMapId(Long mapId);
}
