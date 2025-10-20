package com.example.HomeLibrary.model.mapper;

import com.example.HomeLibrary.model.dto.BookRequestDto;
import com.example.HomeLibrary.model.dto.BookResponseDto;
import com.example.HomeLibrary.model.entities.Book;
import com.example.HomeLibrary.model.enums.ReadingStatus;
import com.example.HomeLibrary.model.entities.Series;

public class BookMapper {

    public static Book toEntity(BookRequestDto dto) {
        Series series = new Series();
        return Book.builder()
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .year(dto.getYear())
                .genre(dto.getGenre())
                .description(dto.getDescription())
                .status(dto.getStatus())
                .rating(dto.getRating())
                .notes(dto.getNotes())
                .build();
    }

    public static BookResponseDto toDto(Book book) {
        return BookResponseDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .genre(book.getGenre())
                .status(book.getStatus())
                .rating(book.getRating())
                .notes(book.getNotes())
                .year(book.getYear())
                .seriesName(book.getSeries() != null ? book.getSeries().getName() : null)
                .build();
    }
}
