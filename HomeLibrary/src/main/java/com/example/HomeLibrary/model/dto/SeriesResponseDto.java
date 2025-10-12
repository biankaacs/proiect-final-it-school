package com.example.HomeLibrary.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SeriesResponseDto {
    private Long id;
    private String name;
    private int bookCount;
}
