package com.forU.hackathon.dto.map;


import com.forU.hackathon.entity.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class MapResponse {
    @Getter
    @AllArgsConstructor
    public static class Info {
        private Long id;
        private String name;
        private Integer count;

        public static Info from(Map map) {
            return new Info(map.getId(), map.getName(), map.getCount());
        }
    }
}
