package com.example.HomeLibrary.model.dto;

import com.example.HomeLibrary.model.enums.WishlistStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WishlistResponseDto {
    private Long id;
    private String title;
    private String author;
    private Integer year;
    private String genre;
    private String description;
    private WishlistStatus status;
    private String notes;
    private String seriesName;
}
