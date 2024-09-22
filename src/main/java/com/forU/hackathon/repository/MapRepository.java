package com.forU.hackathon.repository;

import com.forU.hackathon.entity.Map;
import com.forU.hackathon.entity.MapType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MapRepository extends JpaRepository<Map,Long> {
    List<Map> findAllByType(MapType type);
    @Modifying
    @Query("UPDATE Map m SET m.count = m.count + 1 WHERE m.id = :mapId")
    void incrementCount(Long mapId);

    @Modifying
    @Query("UPDATE Map m SET m.count = m.count - 1 WHERE m.id = :mapId")
    void decrementCount(Long mapId);
}
