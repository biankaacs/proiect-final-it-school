package com.example.HomeLibrary.repo;


import com.example.HomeLibrary.model.entities.WishlistItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepository extends JpaRepository<WishlistItem, Long> {
}
