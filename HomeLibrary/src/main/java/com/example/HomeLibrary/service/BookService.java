package com.example.HomeLibrary.service;

import com.example.HomeLibrary.model.mapper.BookMapper;
import com.example.HomeLibrary.model.dto.BookRequestDto;
import com.example.HomeLibrary.model.dto.BookResponseDto;
import com.example.HomeLibrary.model.entities.Book;
import com.example.HomeLibrary.repo.BookRepository;
import com.example.HomeLibrary.repo.SeriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final SeriesRepository seriesRepository;

    public BookResponseDto addBook(BookRequestDto dto) {
        //Nezd meg a dto.getSeriesId-t h van e erteke.
        //Ha van akkor megnezed hogy van e azzal az ID-val series az adatbazisbal seriesRepo.findBYiD;
        //Series series = repo.findById(dto.getById)
        Book book = BookMapper.toEntity(dto); // add hozza a series valtozot pluszban!
        Book saved = bookRepository.save(book);
        return BookMapper.toDto(saved);
    }

    public List<BookResponseDto> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(BookMapper::toDto)
                .toList();
    }

    public BookResponseDto updateBook(Long id, BookRequestDto dto) {
        Book book = bookRepository.findById(id).orElseThrow();
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setYear(dto.getYear());
        book.setGenre(dto.getGenre());
        book.setDescription(dto.getDescription());
        book.setStatus(dto.getStatus());
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
