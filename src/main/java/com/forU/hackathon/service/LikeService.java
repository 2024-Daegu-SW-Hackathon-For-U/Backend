package com.forU.hackathon.service;

import com.forU.hackathon.entity.LikePlace;
import com.forU.hackathon.entity.Place;
import com.forU.hackathon.repository.LikePlaceRepository;
import com.forU.hackathon.repository.MapRepository;
import com.forU.hackathon.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final MapRepository mapRepository;
    private final PlaceRepository placeRepository;
    private final LikePlaceRepository likePlaceRepository;

    @Transactional
    public void createLikePlace(Long placeId) {
        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new NoSuchElementException("해당 장소가 존재하지 않습니다."));

        LikePlace likePlace = new LikePlace(null, place);
        likePlaceRepository.save(likePlace);
    }
}
