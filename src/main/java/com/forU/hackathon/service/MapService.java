package com.forU.hackathon.service;

import com.forU.hackathon.dto.map.MapRequest;
import com.forU.hackathon.dto.map.MapResponse;
import com.forU.hackathon.entity.Map;
import com.forU.hackathon.repository.MapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MapService {
    private final MapRepository mapRepository;

    public MapResponse.Info create(MapRequest.Create request) {
        Map map = new Map(null, request.getName(), request.getType(), null);
        mapRepository.save(map);
        return MapResponse.Info.from(map);
    }

    public void delete(Long mapId) {
        mapRepository.deleteById(mapId);
    }
}
