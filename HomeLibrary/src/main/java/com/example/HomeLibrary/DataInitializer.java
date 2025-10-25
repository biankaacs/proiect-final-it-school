package com.example.HomeLibrary;

import com.example.HomeLibrary.model.entities.*;
import com.example.HomeLibrary.model.enums.ReadingStatus;
import com.example.HomeLibrary.model.enums.WishlistStatus;
import com.example.HomeLibrary.repo.BookRepository;
import com.example.HomeLibrary.repo.SeriesRepository;
import com.example.HomeLibrary.repo.WishlistRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final BookRepository bookRepository;
    private final SeriesRepository seriesRepository;
    private final WishlistRepository wishlistRepository;
    private final EntityManager entityManager;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        // Delete all existing books and series
        wishlistRepository.deleteAll();
        bookRepository.deleteAll();
        seriesRepository.deleteAll();

        System.out.println("All existing books and series have been deleted.");

        entityManager.createNativeQuery("ALTER SEQUENCE book_id_seq RESTART WITH 1").executeUpdate();
        entityManager.createNativeQuery("ALTER SEQUENCE series_id_seq RESTART WITH 1").executeUpdate();
        entityManager.createNativeQuery("ALTER SEQUENCE wishlist_item_id_seq RESTART WITH 1").executeUpdate();

        // Create Dune series
        Series duneSeries = Series.builder()
                .name("Dune")
                .totalVolumes(6)
                .build();
        seriesRepository.save(duneSeries);

        // Create books for Dune series
        Book book1 = Book.builder()
                .title("Dune")
                .author("Frank Herbert")
                .year(1965)
                .genre("Science Fiction")
                .description("The first novel in the epic Dune saga.")
                .rating(5)
                .notes("Currently reading this classic masterpiece.")
                .status(ReadingStatus.CURRENTLY_READING)
                .series(duneSeries)
                .build();

        Book book2 = Book.builder()
                .title("Dune Messiah")
                .author("Frank Herbert")
                .year(1969)
                .genre("Science Fiction")
                .description("The second book in the Dune series, following Paul Atreides' rise to power.")
                .rating(0)
                .notes("Next on my list after finishing the first book.")
                .status(ReadingStatus.TO_READ)
                .series(duneSeries)
                .build();

        // Standalone books
        Book book3 = Book.builder()
                .title("The Host")
                .author("Stephenie Meyer")
                .year(2008)
                .genre("Science Fiction / Romance")
                .description("A unique story about humanity and identity in a post-invasion world.")
                .rating(4)
                .notes("Surprisingly emotional and well written.")
                .status(ReadingStatus.READ)
                .build();

        Book book4 = Book.builder()
                .title("Pride and Prejudice")
                .author("Jane Austen")
                .year(1813)
                .genre("Romance")
                .description("A timeless story of love, manners, and misunderstanding.")
                .rating(5)
                .notes("A beautifully written classic that never gets old.")
                .status(ReadingStatus.READ)
                .build();

        Book book5 = Book.builder()
                .title("The Kaiju Preservation Society")
                .author("John Scalzi")
                .year(2022)
                .genre("Science Fiction / Adventure")
                .description("A fun and fast-paced novel about protecting giant monsters in an alternate universe.")
                .rating(0)
                .notes("Looks really fun — can’t wait to read it!")
                .status(ReadingStatus.TO_READ)
                .build();

        // Save all
        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);
        bookRepository.save(book4);
        bookRepository.save(book5);

        System.out.println("5 books have been inserted into the database!");

        // Wishlist (Dune 3)
        WishlistItem wish1 = WishlistItem.builder()
                .title("Children of Dune")
                .author("Frank Herbert")
                .genre("Science Fiction")
                .description("Third book in the Dune series")
                .series(duneSeries)
                .status(WishlistStatus.TO_BUY)
                .build();

        WishlistItem wish2 = WishlistItem.builder()
                .title("Phantasma")
                .author("Kaylie Smith")
                .genre("Dark Fantasy Romance")
                .description("A dark fantasy romance set in a haunted mansion with deadly trials")
                .status(WishlistStatus.TO_BUY)
                .build();

        wishlistRepository.save(wish1);
        wishlistRepository.save(wish2);
        System.out.println("Wishlist entries added.");
    }
}
