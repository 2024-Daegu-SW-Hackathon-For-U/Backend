package com.forU.hackathon.dto.place;

import com.forU.hackathon.entity.Place;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class PlaceResponse {
    @Getter
    @AllArgsConstructor
    public static class Info {
        private Long id;
        private String name;
        private String address;
        private Double latitude;
        private Double longitude;

        public static Info from(Place place) {
            return new Info(place.getId(), place.getName(), place.getAddress(), place.getLatitude(), place.getLongitude());
        }
    }
}
