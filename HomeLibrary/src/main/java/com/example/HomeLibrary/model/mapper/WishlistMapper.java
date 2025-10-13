package com.example.HomeLibrary.model.mapper;

import com.example.HomeLibrary.model.dto.WishlistRequestDto;
import com.example.HomeLibrary.model.dto.WishlistResponseDto;
import com.example.HomeLibrary.model.entities.WishlistItem;
import com.example.HomeLibrary.model.entities.WishlistStatus;

public class WishlistMapper {

    public static WishlistItem toEntity(WishlistRequestDto dto) {
        return WishlistItem.builder()
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .status(WishlistStatus.valueOf(String.valueOf(dto.getStatus())))
                .build();
    }

    public static WishlistResponseDto toDto(WishlistItem item) {
        return WishlistResponseDto.builder()
                .id(item.getId())
                .title(item.getTitle())
                .author(item.getAuthor())
                .status(WishlistStatus.valueOf(String.valueOf(item.getStatus())))
                .build();
    }
}
