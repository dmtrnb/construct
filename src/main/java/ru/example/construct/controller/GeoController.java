package ru.example.construct.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.example.construct.service.GeoService;
import ru.example.construct.repository.dto.FeatureCollectionDto;

@RestController
public class GeoController {

    private final GeoService service;

    @Autowired
    public GeoController(GeoService service) {
        this.service = service;
    }

    @PostMapping("/intersections")
    public FeatureCollectionDto intersection(@RequestBody String geoJson) {
        return service.intersection(geoJson);
    }

    @GetMapping("/intersections")
    public FeatureCollectionDto getFeatureCollectionByState(boolean state) {
        return service.getFeatureCollectionByState(state);
    }
}
