package com.example.HomeLibrary.model.dto;

import lombok.Data;

@Data
public class BookRequestDto {
    private String title;
    private String author;
    private int year;
    private String genre;
    private String description;
    private String status; //Hasznald az enumot
    private Integer rating;
    private String notes;
    private Long seriesId; // optional
}
