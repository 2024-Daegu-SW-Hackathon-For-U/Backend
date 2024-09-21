package com.forU.hackathon.repository;

import com.forU.hackathon.entity.Map;
import com.forU.hackathon.entity.MapType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MapRepository extends JpaRepository<Map,Long> {
    List<Map> findAllByType(MapType type);
}
