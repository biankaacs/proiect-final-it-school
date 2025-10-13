package com.example.HomeLibrary.model.dto;

import com.example.HomeLibrary.model.entities.ReadingStatus;
import lombok.Data;

@Data
public class BookRequestDto {
    private String title;
    private String author;
    private int year;
    private String genre;
    private String description;
    private ReadingStatus status;
    private Integer rating;
    private String notes;
    private Long seriesId; // optional
}
