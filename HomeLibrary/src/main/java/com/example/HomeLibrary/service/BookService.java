package com.example.HomeLibrary.service;

import com.example.HomeLibrary.model.enums.ReadingStatus;
import com.example.HomeLibrary.model.mapper.BookMapper;
import com.example.HomeLibrary.model.dto.BookRequestDto;
import com.example.HomeLibrary.model.dto.BookResponseDto;
import com.example.HomeLibrary.model.entities.Book;
import com.example.HomeLibrary.repo.BookRepository;
import com.example.HomeLibrary.repo.SeriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final SeriesRepository seriesRepository;

    public BookResponseDto addBook(BookRequestDto dto) {
        Book book = BookMapper.toEntity(dto);
        Optional.ofNullable(dto.getSeriesId())
                .map(id -> seriesRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Series not found")))
                .ifPresent(book::setSeries);
        Book saved = bookRepository.save(book);
        return BookMapper.toDto(saved);
    }


    public List<BookResponseDto> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(BookMapper::toDto)
                .toList();
    }

    public BookResponseDto updateBook(Long id, BookRequestDto dto) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found with id: " + id));

        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setYear(dto.getYear());
        book.setGenre(dto.getGenre());
        book.setDescription(dto.getDescription());
        book.setRating(dto.getRating());
        book.setNotes(dto.getNotes());

        if (dto.getStatus() != null) {
            book.setStatus(dto.getStatus());
        }

        if (dto.getSeriesId() != null) {
            var series = seriesRepository.findById(dto.getSeriesId())
                    .orElseThrow(() -> new RuntimeException("Series not found with id: " + dto.getSeriesId()));
            book.setSeries(series);
        }

        Book updated = bookRepository.save(book);
        return BookMapper.toDto(updated);
    }

    public List<BookResponseDto> findByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title)
                .stream()
                .map(BookMapper::toDto)
                .toList();
    }

    public List<BookResponseDto> findByAuthor(String author) {
        return bookRepository.findByAuthorContainingIgnoreCase(author)
                .stream()
                .map(BookMapper::toDto)
                .toList();
    }

    public List<BookResponseDto> findByGenre(String genre) {
        return bookRepository.findByGenreContainingIgnoreCase(genre)
                .stream()
                .map(BookMapper::toDto)
                .toList();
    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new IllegalArgumentException("Book with ID " + id + " not found");
        }
        bookRepository.deleteById(id);
    }
}
