package com.example.HomeLibrary;

import com.example.HomeLibrary.model.entities.Book;
import com.example.HomeLibrary.model.entities.Series;
import com.example.HomeLibrary.model.entities.WishlistItem;
import com.example.HomeLibrary.model.enums.ReadingStatus;
import com.example.HomeLibrary.model.enums.WishlistStatus;
import com.example.HomeLibrary.repo.BookRepository;
import com.example.HomeLibrary.repo.SeriesRepository;
import com.example.HomeLibrary.repo.WishlistRepository;
import com.example.HomeLibrary.service.StatisticsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class StatisticsServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private WishlistRepository wishlistRepository;

    @Mock
    private SeriesRepository seriesRepository;

    @InjectMocks
    private StatisticsService statisticsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetStatistics_ReturnsCorrectCounts() {
        Book book1 = Book.builder().status(ReadingStatus.READ).build();
        Book book2 = Book.builder().status(ReadingStatus.TO_READ).build();
        when(bookRepository.findAll()).thenReturn(List.of(book1, book2));

        WishlistItem wishlistItem = WishlistItem.builder()
                .status(WishlistStatus.TO_BUY)
                .build();
        when(wishlistRepository.findAll()).thenReturn(List.of(wishlistItem));

        Series dune = Series.builder()
                .name("Dune")
                .totalVolumes(6)
                .books(List.of(book1, book2))
                .build();
        when(seriesRepository.findAll()).thenReturn(List.of(dune));

        Map<String, Object> stats = statisticsService.getStatistics();

        assertEquals(2, stats.get("totalBooks"));
        assertEquals(1L, stats.get("booksRead"));
        assertEquals(1L, stats.get("booksToRead"));
        assertEquals(1, stats.get("wishlistCount"));
        assertEquals(1, stats.get("seriesCount"));

        Map<String, Map<String, Integer>> seriesInfo =
                (Map<String, Map<String, Integer>>) stats.get("seriesInfo");
        assertTrue(seriesInfo.containsKey("Dune"));
        assertEquals(6, seriesInfo.get("Dune").get("totalVolumes"));
        assertEquals(4, seriesInfo.get("Dune").get("missingBooks"));
    }

    @Test
    void testGetStatistics_WhenEmptyDatabase_ReturnsZeroCounts() {
        when(bookRepository.findAll()).thenReturn(List.of());
        when(wishlistRepository.findAll()).thenReturn(List.of());
        when(seriesRepository.findAll()).thenReturn(List.of());

        Map<String, Object> stats = statisticsService.getStatistics();

        assertEquals(0, stats.get("totalBooks"));
        assertEquals(0L, stats.get("booksRead"));
        assertEquals(0L, stats.get("booksToRead"));
        assertEquals(0L, stats.get("booksReading"));
        assertEquals(0, stats.get("wishlistCount"));
        assertEquals(0, stats.get("seriesCount"));

        Map<?, ?> wishlistByStatus = (Map<?, ?>) stats.get("wishlistByStatus");
        Map<?, ?> seriesInfo = (Map<?, ?>) stats.get("seriesInfo");

        assertTrue(wishlistByStatus.isEmpty());
        assertTrue(seriesInfo.isEmpty());
    }
}