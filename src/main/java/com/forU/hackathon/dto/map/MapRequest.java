package com.forU.hackathon.dto.map;

import com.forU.hackathon.entity.MapType;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class MapRequest {
    @Getter
    @AllArgsConstructor
    public static class Create {
        private String name;
        private MapType type;
    }
}
