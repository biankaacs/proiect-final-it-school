package com.example.HomeLibrary.model.mapper;

import com.example.HomeLibrary.model.dto.SeriesRequestDto;
import com.example.HomeLibrary.model.dto.SeriesResponseDto;
import com.example.HomeLibrary.model.entities.Series;

public class SeriesMapper {

    public static Series toEntity(SeriesRequestDto dto) {
        return Series.builder()
                .name(dto.getName())
                .build();
    }

    public static SeriesResponseDto toDto(Series series) {
        int count = (series.getBooks() != null) ? series.getBooks().size() : 0;
        return SeriesResponseDto.builder()
                .id(series.getId())
                .name(series.getName())
                .bookCount(count)
                .build();
    }
}
