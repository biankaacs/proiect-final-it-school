package com.example.HomeLibrary.controller;


import com.example.HomeLibrary.model.dto.WishlistRequestDto;
import com.example.HomeLibrary.model.dto.WishlistResponseDto;
import com.example.HomeLibrary.service.WishlistService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
@RequiredArgsConstructor
@Tag(name = "Wishlist API", description = "Manage your personal wishlist of books to buy, borrow, or await release.")
public class WishlistController {

    private final WishlistService wishlistService;

    @Operation(summary = "Get all wishlist items", description = "Retrieve all items currently in your wishlist.")
    @GetMapping
    public List<WishlistResponseDto> getAll() {
        return wishlistService.getAllItems();
    }
    @Operation(
            summary = "Add a book to wishlist",
            description = "Add a new book to your wishlist.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Book details to add",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = WishlistRequestDto.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Example wishlist book",
                                            value = """
                                                    {
                                                      "title": "Phantasma",
                                                      "author": "Christina Bauer",
                                                      "status": "TO_BUY"
                                                    }
                                                    """
                                    )
                            }
                    )
            )
    )
    @PostMapping
    public WishlistResponseDto add(@RequestBody WishlistRequestDto dto) {
        return wishlistService.addItem(dto);
    }


    @Operation(
            summary = "Update a wishlist item",
            description = "Update details of an existing wishlist book.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Updated book details",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = WishlistRequestDto.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Update example",
                                            value = """
                                                    {
                                                       "title": "Phantasma",
                                                       "author": "Christina Bauer",
                                                       "status": "TO_BUY"
                                                    }
                                                    """
                                    )
                            }
                    )
            )
    )
    @PutMapping("/{id}")
    public WishlistResponseDto update(@PathVariable Long id, @RequestBody WishlistRequestDto dto) {
        return wishlistService.updateItem(id, dto);
    }

    @Operation(summary = "Delete a wishlist item by ID", description = "Remove a book from your wishlist.")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        wishlistService.deleteItem(id);
    }
}
