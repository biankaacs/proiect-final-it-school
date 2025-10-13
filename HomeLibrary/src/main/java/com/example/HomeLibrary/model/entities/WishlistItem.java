package com.example.HomeLibrary.model.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WishlistItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String genre;
    private String description;

    @Enumerated(EnumType.STRING)
    private WishlistStatus status; // "de cumpărat", "de împrumutat", "aștept lansarea"

    @ManyToOne
    private Series series;
}
