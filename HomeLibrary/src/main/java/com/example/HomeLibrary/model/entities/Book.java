package com.example.HomeLibrary.model.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String title;
    private String author;
    private int year;
    private String genre;
    private String description;

    @Enumerated(EnumType.STRING) // így a PostgreSQL tárolja a státuszt szövegként, nem számként.
    private ReadingStatus status; // "de citit", "în curs de citire", "citită"

    private Integer rating; // 1-5
    private String notes;

    @ManyToOne
    @JoinColumn(name = "series_id")
    private Series series;
}
