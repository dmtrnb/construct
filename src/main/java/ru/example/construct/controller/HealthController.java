package ru.example.construct.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Date;

@RestController
public class HealthController {

    @GetMapping("/health/check")
    public String health() {
        return "Hello, now is " + Date.from(Instant.now());
    }
}
