package com.example.HomeLibrary.model.mapper;

import com.example.HomeLibrary.model.dto.WishlistRequestDto;
import com.example.HomeLibrary.model.dto.WishlistResponseDto;
import com.example.HomeLibrary.model.entities.WishlistItem;
import com.example.HomeLibrary.model.enums.WishlistStatus;

public class WishlistMapper {

    public static WishlistItem toEntity(WishlistRequestDto dto) {
        return WishlistItem.builder()
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .status(dto.getStatus())
                .build();
    }

    public static WishlistResponseDto toDto(WishlistItem item) {
        return WishlistResponseDto.builder()
                .id(item.getId())
                .title(item.getTitle())
                .author(item.getAuthor())
                .year(item.getYear() != null ? item.getYear() : null)
                .genre(item.getGenre())
                .description(item.getDescription())
                .status(item.getStatus())
                .notes(item.getNotes() != null ? item.getNotes() : "")
                .seriesName(item.getSeries() != null ? item.getSeries().getName() : null)
                .build();
    }
}
