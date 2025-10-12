package com.example.HomeLibrary.service;

import com.example.HomeLibrary.model.mapper.SeriesMapper;
import com.example.HomeLibrary.model.dto.SeriesRequestDto;
import com.example.HomeLibrary.model.dto.SeriesResponseDto;
import com.example.HomeLibrary.model.entities.Series;
import com.example.HomeLibrary.repo.SeriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeriesService {

    private final SeriesRepository seriesRepository;

    public SeriesResponseDto addSeries(SeriesRequestDto dto) {
        Series series = SeriesMapper.toEntity(dto);
        Series saved = seriesRepository.save(series);
        return SeriesMapper.toDto(saved);
    }

    public List<SeriesResponseDto> getAllSeries() {
        return seriesRepository.findAll().stream()
                .map(SeriesMapper::toDto)
                .toList();
    }

    public SeriesResponseDto updateSeries(Long id, SeriesRequestDto dto) {
        Series series = seriesRepository.findById(id).orElseThrow();
        series.setName(dto.getName());
        Series updated = seriesRepository.save(series);
        return SeriesMapper.toDto(updated);
    }

    public void deleteSeries(Long id) {
        seriesRepository.deleteById(id);
    }
}
