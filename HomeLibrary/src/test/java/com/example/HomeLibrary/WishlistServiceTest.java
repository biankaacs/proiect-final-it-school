package com.example.HomeLibrary;

import com.example.HomeLibrary.model.dto.WishlistRequestDto;
import com.example.HomeLibrary.model.dto.WishlistResponseDto;
import com.example.HomeLibrary.model.entities.Book;
import com.example.HomeLibrary.model.entities.Series;
import com.example.HomeLibrary.model.entities.WishlistItem;
import com.example.HomeLibrary.model.enums.ReadingStatus;
import com.example.HomeLibrary.model.enums.WishlistStatus;
import com.example.HomeLibrary.repo.BookRepository;
import com.example.HomeLibrary.repo.WishlistRepository;
import com.example.HomeLibrary.service.WishlistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class WishlistServiceTest {

    @Mock
    private WishlistRepository wishlistRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private WishlistService wishlistService;

    private WishlistItem sampleItem;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Series dune = new Series();
        dune.setName("Dune");

        sampleItem = new WishlistItem();
        sampleItem.setId(1L);
        sampleItem.setTitle("Children of Dune");
        sampleItem.setAuthor("Frank Herbert");
        sampleItem.setGenre("Science Fiction");
        sampleItem.setDescription("Third book in the Dune series");
        sampleItem.setStatus(WishlistStatus.TO_BUY);
        sampleItem.setYear(1976);
        sampleItem.setSeries(dune);
    }

    @Test
    void addItem_shouldSaveWishlistItem() {
        WishlistRequestDto dto = new WishlistRequestDto();
        dto.setTitle("Children of Dune");
        dto.setAuthor("Frank Herbert");
        dto.setStatus(WishlistStatus.TO_BUY);

        when(wishlistRepository.save(any(WishlistItem.class))).thenReturn(sampleItem);

        WishlistResponseDto result = wishlistService.addItem(dto);

        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("Children of Dune");

        verify(wishlistRepository, times(1)).save(any(WishlistItem.class));
    }

    @Test
    void moveToLibrary_shouldMoveWishlistItemToBookRepository() {
        when(wishlistRepository.findById(1L)).thenReturn(Optional.of(sampleItem));

        wishlistService.moveToLibrary(1L);

        ArgumentCaptor<Book> bookCaptor = ArgumentCaptor.forClass(Book.class);
        verify(bookRepository).save(bookCaptor.capture());
        verify(wishlistRepository).delete(sampleItem);

        Book savedBook = bookCaptor.getValue();

        assertThat(savedBook.getTitle()).isEqualTo("Children of Dune");
        assertThat(savedBook.getStatus()).isEqualTo(ReadingStatus.TO_READ);
        assertThat(savedBook.getSeries().getName()).isEqualTo("Dune");
    }
}