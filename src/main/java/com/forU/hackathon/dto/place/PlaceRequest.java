package com.forU.hackathon.dto.place;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class PlaceRequest {
    @Getter
    @AllArgsConstructor
    public static class Create {
        private Long mapId;
        private Long placeId;
        private String name;
        private String address;
        private Double latitude;
        private Double longitude;
    }
}
