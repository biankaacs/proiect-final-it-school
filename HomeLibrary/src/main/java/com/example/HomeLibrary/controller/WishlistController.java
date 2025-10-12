package com.example.HomeLibrary.controller;


import com.example.HomeLibrary.model.dto.WishlistRequestDto;
import com.example.HomeLibrary.model.dto.WishlistResponseDto;
import com.example.HomeLibrary.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
@RequiredArgsConstructor
public class WishlistController {

    private final WishlistService wishlistService;

    @GetMapping
    public List<WishlistResponseDto> getAll() {
        return wishlistService.getAllItems();
    }

    @PostMapping
    public WishlistResponseDto add(@RequestBody WishlistRequestDto dto) {
        return wishlistService.addItem(dto);
    }

    @PutMapping("/{id}")
    public WishlistResponseDto update(@PathVariable Long id, @RequestBody WishlistRequestDto dto) {
        return wishlistService.updateItem(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        wishlistService.deleteItem(id);
    }
}
