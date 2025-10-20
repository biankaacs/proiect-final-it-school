package com.example.HomeLibrary.controller;

import com.example.HomeLibrary.model.dto.BookRequestDto;
import com.example.HomeLibrary.model.dto.BookResponseDto;
import com.example.HomeLibrary.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
@Tag(name = "Books API", description = "Endpoints for managing books in your personal library.")
public class BookController {

    private final BookService bookService;

    @Operation(summary = "Get all books", description = "Retrieve a list of all books in the library.")
    @ApiResponse(responseCode = "200", description = "Books retrieved successfully.")
    @GetMapping
    public List<BookResponseDto> getAll() {
        return bookService.getAllBooks();
    }

    @Operation(summary = "Search books by title", description = "Find books containing the given title (case-insensitive).")
    @GetMapping("/search/title")
    public List<BookResponseDto> searchByTitle(@RequestParam String title) {
        return bookService.findByTitle(title);
    }

    @Operation(summary = "Search books by author", description = "Find books written by the given author (case-insensitive).")
    @GetMapping("/search/author")
    public List<BookResponseDto> searchByAuthor(@RequestParam String author) {
        return bookService.findByAuthor(author);
    }

    @Operation(summary = "Search books by genre", description = "Find books matching the given genre (case-insensitive).")
    @GetMapping("/search/genre")
    public List<BookResponseDto> searchByGenre(@RequestParam String genre) {
        return bookService.findByGenre(genre);
    }

    @Operation(summary = "Add a new book",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Example of a book to add",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = BookRequestDto.class),
                            examples = @ExampleObject(
                                    name = "Warm Bodies example",
                                    value = "{\n" +
                                            "  \"title\": \"Warm Bodies\",\n" +
                                            "  \"author\": \"Isaac Marion\",\n" +
                                            "  \"year\": 2010,\n" +
                                            "  \"genre\": \"Fantasy / Horror / Romance\",\n" +
                                            "  \"description\": \"The story is about a zombie who gradually regains his human emotions while falling in love with a living girl.\",\n" +
                                            "  \"status\": \"TO_READ\",\n" +
                                            "  \"rating\": 0,\n" +
                                            "  \"notes\": \"A unique, heartfelt, and humorous story about zombies.\",\n" +
                                            "  \"seriesId\": null\n" +
                                            "}"
                            )
                    )
            ))
    @PostMapping
    public  ResponseEntity<BookResponseDto> addBook(@RequestBody BookRequestDto bookRequestDto) {
        BookResponseDto savedBook = bookService.addBook(bookRequestDto);
        return ResponseEntity.ok(savedBook);
    }

    @Operation(summary = "Delete a book", description = "Delete a book from the library by ID.")
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }


    @Operation(summary = "Update a book", description = "Update details of a book by ID.")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Updated book data",
            required = true,
            content = @Content(
                    schema = @Schema(implementation = BookRequestDto.class),
                    examples = @ExampleObject(
                            name = "Update Book Example",
                            value = """
                                {
                                  "title": "Dune",
                                  "author": "Frank Herbert",
                                  "year": 1965,
                                  "genre": "Science Fiction",
                                  "description": "The first novel in the epic Dune saga.",
                                  "status": "READ",
                                  "rating": 4,
                                  "notes": "Finished reading this classic masterpiece.",
                                  "seriesId": 1
                                }
                                """
                    )
            )
    )
    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDto> updateBook(
            @PathVariable Long id,
            @org.springframework.web.bind.annotation.RequestBody BookRequestDto dto
    ) {
        BookResponseDto updatedBook = bookService.updateBook(id, dto);
        return ResponseEntity.ok(updatedBook);
    }

}
