package com.example.HomeLibrary.model.dto;

import com.example.HomeLibrary.model.enums.WishlistStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class WishlistRequestDto {
    @Schema(description = "ID of the wishlist item (used for updates)", example = "1")
    private Integer id;

    @Schema(description = "Title of the book", example = "Phantasma")
    private String title;

    @Schema(description = "Author of the book", example = "Christina Bauer")
    private String author;

    @Schema(description = "Wishlist status", example = "TO_BUY")
    private WishlistStatus status;
}
