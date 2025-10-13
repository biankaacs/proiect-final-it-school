package com.example.HomeLibrary.model.dto;

import com.example.HomeLibrary.model.entities.WishlistStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WishlistResponseDto {
    private Long id;
    private String title;
    private String author;
    private WishlistStatus status;
}
