package com.example.HomeLibrary.model.mapper;

import com.example.HomeLibrary.model.dto.WishlistRequestDto;
import com.example.HomeLibrary.model.dto.WishlistResponseDto;
import com.example.HomeLibrary.model.entities.WishlistItem;

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
                .status(item.getStatus())
                .build();
    }
}
