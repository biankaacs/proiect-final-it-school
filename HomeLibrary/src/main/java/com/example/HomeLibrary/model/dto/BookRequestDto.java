package com.example.HomeLibrary.model.dto;

import com.example.HomeLibrary.model.enums.ReadingStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Request body for adding or updating a book")
public class BookRequestDto {
    @Schema(description = "Title of the book", example = "Dune")
    private String title;

    @Schema(description = "Author of the book", example = "Frank Herbert")
    private String author;

    @Schema(description = "Year of publication", example = "1965")
    private int year;

    @Schema(description = "Genre of the book", example = "Science Fiction")
    private String genre;

    @Schema(description = "Description of the book", example = "First book in the Dune saga")
    private String description;

    @Schema(description = "Reading status of the book", example = "READ")
    private ReadingStatus status;

    @Schema(description = "Rating of the book", example = "4")
    private Integer rating;

    @Schema(description = "Additional notes about the book", example = "Finished reading this classic masterpiece.")
    private String notes;

    @Schema(description = "ID of the series, if applicable", example = "1")
    private Long seriesId; // optional
}
