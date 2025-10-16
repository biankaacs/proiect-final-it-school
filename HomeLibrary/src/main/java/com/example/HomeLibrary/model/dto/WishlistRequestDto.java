package com.example.HomeLibrary.model.dto;

import com.example.HomeLibrary.model.enums.WishlistStatus;
import lombok.Data;

@Data
public class WishlistRequestDto {
    private String title;
    private String author;
    private WishlistStatus status;
}
