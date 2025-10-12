package com.example.HomeLibrary.controller;


import com.example.HomeLibrary.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping
    public Map<String, Object> getStats() {
        return statisticsService.getStatistics();
    }
}
//This statistic can be easily expanded, for example:
//average book ratings
//breakdown by wishlist status
//exact number of missing volumes in a series (if there is a totalVolumes field in the Series entity)
