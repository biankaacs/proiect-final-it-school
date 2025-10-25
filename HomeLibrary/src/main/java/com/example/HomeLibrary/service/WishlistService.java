package com.example.HomeLibrary.service;

import com.example.HomeLibrary.model.entities.Book;
import com.example.HomeLibrary.model.enums.ReadingStatus;
import com.example.HomeLibrary.model.enums.WishlistStatus;
import com.example.HomeLibrary.model.mapper.WishlistMapper;
import com.example.HomeLibrary.model.dto.WishlistRequestDto;
import com.example.HomeLibrary.model.dto.WishlistResponseDto;
import com.example.HomeLibrary.model.entities.WishlistItem;
import com.example.HomeLibrary.repo.BookRepository;
import com.example.HomeLibrary.repo.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final BookRepository bookRepository;

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
        item.setStatus(WishlistStatus.valueOf(String.valueOf(dto.getStatus())));
        WishlistItem updated = wishlistRepository.save(item);
        return WishlistMapper.toDto(updated);
    }

    public void deleteItem(Long id) {
        wishlistRepository.deleteById(id);
    }

    public Book moveToLibrary(Long wishlistItemId) {
        WishlistItem item = wishlistRepository.findById(wishlistItemId)
                .orElseThrow(() -> new RuntimeException("Wishlist item not found"));

        Book book = Book.builder()
                .title(item.getTitle())
                .author(item.getAuthor())
                .year(item.getYear() != null ? item.getYear() : 0)
                .genre(item.getGenre())
                .description(item.getDescription())
                .description(item.getDescription())
                .status(ReadingStatus.TO_READ)
                .notes(item.getNotes())
                .rating(0)
                .series(item.getSeries() != null ? item.getSeries() : null)
                .build();

        bookRepository.save(book);
        wishlistRepository.delete(item);

        return book;
    }
}
