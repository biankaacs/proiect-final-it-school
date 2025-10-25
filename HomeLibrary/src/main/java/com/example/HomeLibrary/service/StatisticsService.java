package com.example.HomeLibrary.service;

import com.example.HomeLibrary.model.entities.Book;
import com.example.HomeLibrary.model.entities.Series;
import com.example.HomeLibrary.model.entities.WishlistItem;
import com.example.HomeLibrary.model.enums.ReadingStatus;
import com.example.HomeLibrary.model.enums.WishlistStatus;
import com.example.HomeLibrary.repo.BookRepository;
import com.example.HomeLibrary.repo.SeriesRepository;
import com.example.HomeLibrary.repo.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final BookRepository bookRepository;
    private final WishlistRepository wishlistRepository;
    private final SeriesRepository seriesRepository;

    public Map<String, Object> getStatistics() {
        Map<String, Object> stats = new HashMap<>();

        List<Book> books = bookRepository.findAll();
        List<WishlistItem> wishlist = wishlistRepository.findAll();
        List<Series> seriesList = seriesRepository.findAll();

        // Breakdown by book status
        stats.put("totalBooks", books.size());
        stats.put("booksRead", books.stream().filter(b -> b.getStatus() == ReadingStatus.READ).count());
        stats.put("booksReading", books.stream().filter(b -> b.getStatus() == ReadingStatus.CURRENTLY_READING).count());
        stats.put("booksToRead", books.stream().filter(b -> b.getStatus() == ReadingStatus.TO_READ).count());
        stats.put("wishlistCount", wishlist.size());
        stats.put("seriesCount", seriesList.size());

        // Wishlist by status
        Map<WishlistStatus, Long> wishlistByStatus = wishlist.stream()
                .collect(Collectors.groupingBy(WishlistItem::getStatus, Collectors.counting()));
        stats.put("wishlistByStatus", wishlistByStatus);

        // Number of series and missing volumes
        Map<String, Map<String, Integer>> seriesInfo = getStringMapMap(seriesList);
        stats.put("seriesInfo", seriesInfo);

        return stats;
    }

    private static Map<String, Map<String, Integer>> getStringMapMap(List<Series> seriesList) {
        Map<String, Map<String, Integer>> seriesInfo = new HashMap<>();
        for (Series s : seriesList) {
            int currentBooks = s.getBooks() != null ? s.getBooks().size() : 0;
            int totalVolumes = s.getTotalVolumes() != null ? s.getTotalVolumes() : currentBooks;
            int missing = totalVolumes - currentBooks;
            Map<String, Integer> info = new HashMap<>();
            info.put("currentBooks", currentBooks);
            info.put("missingBooks", missing);
            info.put("totalVolumes", totalVolumes);
            seriesInfo.put(s.getName(), info);
        }
        return seriesInfo;
    }
}
