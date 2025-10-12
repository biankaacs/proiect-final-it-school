package com.example.HomeLibrary.controller;

import com.example.HomeLibrary.model.dto.SeriesRequestDto;
import com.example.HomeLibrary.model.dto.SeriesResponseDto;
import com.example.HomeLibrary.model.entities.Series;
import com.example.HomeLibrary.service.SeriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/series")
@RequiredArgsConstructor
public class SeriesController {

    private final SeriesService seriesService;

    @GetMapping
    public List<SeriesResponseDto> getAll() {
        return seriesService.getAllSeries();
    }

    @PostMapping
    public SeriesResponseDto add(@RequestBody SeriesRequestDto dto) {
        return seriesService.addSeries(dto);
    }

    @PutMapping("/{id}")
    public SeriesResponseDto update(@PathVariable Long id, @RequestBody SeriesRequestDto dto) {
        return seriesService.updateSeries(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        seriesService.deleteSeries(id);
    }
}
