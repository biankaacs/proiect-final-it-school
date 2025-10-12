package com.example.HomeLibrary.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookResponseDto {
    private Long id;
    private String title;
    private String author;
    private String genre;
    private String status;
    private Integer rating;
    private String seriesName;
}
