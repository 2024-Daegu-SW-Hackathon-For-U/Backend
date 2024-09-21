package com.forU.hackathon.service;

import com.forU.hackathon.dto.map.MapRequest;
import com.forU.hackathon.dto.map.MapResponse;
import com.forU.hackathon.entity.Map;
import com.forU.hackathon.entity.MapType;
import com.forU.hackathon.repository.MapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public List<MapResponse.Info> getAll(MapType mapType) {
        List<Map> maps;
        if (mapType == null) {
            maps = mapRepository.findAll();
        } else {
            maps = mapRepository.findAllByType(mapType);
        }
        return maps
                .stream()
                .map(MapResponse.Info::from)
                .toList();
    }
}
