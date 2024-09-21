package com.forU.hackathon.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Place {
    @Id
    private Long id;

    private String name;

    private String address;

    private Double latitude;

    private Double longitude;

    @OneToMany(mappedBy = "place")
    private List<PlaceInMap> placeInMap;
}
