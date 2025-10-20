package com.example.HomeLibrary.config;

import com.example.HomeLibrary.model.dto.BookRequestDto;
import com.example.HomeLibrary.model.enums.ReadingStatus;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Schema;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI homeLibraryOpenAPI() {
        BookRequestDto exampleBook = new BookRequestDto();
        exampleBook.setTitle("Warm Bodies");
        exampleBook.setAuthor("Isaac Marion");
        exampleBook.setYear(2010);
        exampleBook.setGenre("Fantasy / Horror / Romance");
        exampleBook.setDescription("The story is about a zombie who gradually regains his human emotions while falling in love with a living girl.");
        exampleBook.setStatus(ReadingStatus.TO_READ);
        exampleBook.setRating(0);
        exampleBook.setNotes("A unique, heartfelt, and humorous story about zombies.");
        exampleBook.setSeriesId(null);

        return new OpenAPI()
                .info(new Info()
                        .title("Home Library API")
                        .description("A simple personal library management application with books, series, and wishlist features.")
                        .version("1.0.0"))
                .components(new Components()
                        .addSchemas("BookRequestDto", new Schema<BookRequestDto>()
                                .example(exampleBook)
                        )
                );
    }
}