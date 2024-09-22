package com.forU.hackathon.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Map {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private MapType type;

    @Column(nullable = false)
    private int count = 0;

    @OneToMany(mappedBy = "map")
    private List<PlaceInMap> placesInMap;

    //ToDo: 유저 엔티티 추가 후 연관관계 매핑 필요
}
