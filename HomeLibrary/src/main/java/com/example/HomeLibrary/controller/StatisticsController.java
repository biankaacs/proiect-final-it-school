package com.example.HomeLibrary.controller;


import com.example.HomeLibrary.service.StatisticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/statistics")
@RequiredArgsConstructor
@Tag(name = "Statistics API", description = "Provides summary statistics about your personal library, such as total books, reading status distribution, and wishlist items.")
public class StatisticsController {

    private final StatisticsService statisticsService;

    @Operation(
            summary = "Get library statistics",
            description = "Returns key statistics about your book collection and wishlist, including counts by reading status."
    )
    @GetMapping
    public Map<String, Object> getStats() {
        return statisticsService.getStatistics();
    }
}

