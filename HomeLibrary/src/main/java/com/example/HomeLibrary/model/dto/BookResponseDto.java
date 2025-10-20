package com.example.HomeLibrary.model.dto;

import com.example.HomeLibrary.model.enums.ReadingStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookResponseDto {
    private Long id;
    private String title;
    private String author;
    private String genre;
    private ReadingStatus status;
    private Integer rating;
    private String notes;
    private Integer year;
    private String seriesName;
}
