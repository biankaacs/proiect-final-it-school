package com.example.HomeLibrary;

import com.example.HomeLibrary.model.dto.WishlistRequestDto;
import com.example.HomeLibrary.model.enums.WishlistStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class WishlistControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addBookToWishlist_shouldReturnCreatedItem() throws Exception {
        WishlistRequestDto dto = new WishlistRequestDto();
        dto.setTitle("Children of Dune");
        dto.setAuthor("Frank Herbert");
        dto.setStatus(WishlistStatus.TO_BUY);

        ResultActions response = mockMvc.perform(post("/api/wishlist")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Children of Dune"))
                .andExpect(jsonPath("$.author").value("Frank Herbert"))
                .andExpect(jsonPath("$.status").value("TO_BUY"));
    }

    @Test
    void moveWishlistItemToLibrary_shouldCreateBookAndDeleteWishlistItem() throws Exception {
        WishlistRequestDto dto = new WishlistRequestDto();
        dto.setTitle("Dune Messiah");
        dto.setAuthor("Frank Herbert");
        dto.setStatus(WishlistStatus.TO_BUY);

        String responseBody = mockMvc.perform(post("/api/wishlist")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Long wishlistId = objectMapper.readTree(responseBody).get("id").asLong();

        mockMvc.perform(post("/api/wishlist/" + wishlistId + "/move-to-library"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Dune Messiah"))
                .andExpect(jsonPath("$.author").value("Frank Herbert"))
                .andExpect(jsonPath("$.status").value("TO_READ"));
    }
}