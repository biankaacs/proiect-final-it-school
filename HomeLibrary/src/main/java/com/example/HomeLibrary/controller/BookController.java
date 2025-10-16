package com.example.HomeLibrary.controller;

import com.example.HomeLibrary.model.dto.BookRequestDto;
import com.example.HomeLibrary.model.dto.BookResponseDto;
import com.example.HomeLibrary.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public List<BookResponseDto> getAll() {
        return bookService.getAllBooks();
    }

    @GetMapping("/search/title")
    public List<BookResponseDto> searchByTitle(@RequestParam String title) {
        return bookService.findByTitle(title);
    }

    @GetMapping("/search/author")
    public List<BookResponseDto> searchByAuthor(@RequestParam String author) {
        return bookService.findByAuthor(author);
    }

    @GetMapping("/search/genre")
    public List<BookResponseDto> searchByGenre(@RequestParam String genre) {
        return bookService.findByGenre(genre);
    }

    @PostMapping
    public BookResponseDto addBook(@RequestBody BookRequestDto dto) {
        return bookService.addBook(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDto> updateBook(@PathVariable Long id, @RequestBody BookRequestDto dto) {
        BookResponseDto updatedBook = bookService.updateBook(id, dto);
        return ResponseEntity.ok(updatedBook);
    }

}
