package com.example.HomeLibrary.service;

import com.example.HomeLibrary.model.mapper.WishlistMapper;
import com.example.HomeLibrary.model.dto.WishlistRequestDto;
import com.example.HomeLibrary.model.dto.WishlistResponseDto;
import com.example.HomeLibrary.model.entities.WishlistItem;
import com.example.HomeLibrary.repo.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishlistService {

    private final WishlistRepository wishlistRepository;

    public WishlistResponseDto addItem(WishlistRequestDto dto) {
        WishlistItem item = WishlistMapper.toEntity(dto);
        WishlistItem saved = wishlistRepository.save(item);
        return WishlistMapper.toDto(saved);
    }

    public List<WishlistResponseDto> getAllItems() {
        return wishlistRepository.findAll()
                .stream()
                .map(WishlistMapper::toDto)
                .toList();
    }

    public WishlistResponseDto updateItem(Long id, WishlistRequestDto dto) {
        WishlistItem item = wishlistRepository.findById(id).orElseThrow();
        item.setTitle(dto.getTitle());
        item.setAuthor(dto.getAuthor());
        item.setStatus(dto.getStatus());
        WishlistItem updated = wishlistRepository.save(item);
        return WishlistMapper.toDto(updated);
    }

    public void deleteItem(Long id) {
        wishlistRepository.deleteById(id);
    }
}
