package org.demo.parcialmagnetoLuciano.controllers;

import org.demo.parcialmagnetoLuciano.dto.StatsResponse;
import org.demo.parcialmagnetoLuciano.services.StatsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
public class StatsController {

    private final StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping
    public StatsResponse getStats() {
        return statsService.getStats();
    }
}