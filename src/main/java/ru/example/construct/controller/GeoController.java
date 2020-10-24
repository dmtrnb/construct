package ru.example.construct.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.example.construct.service.GeoService;

import java.time.Instant;
import java.util.Date;

@RestController
public class GeoController {

    private final GeoService service;

    @Autowired
    public GeoController(GeoService service) {
        this.service = service;
    }

    @PostMapping("/intersections")
    public String intersection(@RequestBody String geoJson) {
        return service.intersection(geoJson);
}

    @GetMapping("/health/check")
    public String health() {
        return "Hello, now is " + Date.from(Instant.now());
    }

//    @RequestMapping
//    public String home() {
//        return null;
//    }
}
