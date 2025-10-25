package com.example.HomeLibrary.controller;

import com.example.HomeLibrary.model.dto.SeriesRequestDto;
import com.example.HomeLibrary.model.dto.SeriesResponseDto;
import com.example.HomeLibrary.model.entities.Series;
import com.example.HomeLibrary.service.SeriesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/series")
@RequiredArgsConstructor
@Tag(name = "Series API", description = "Endpoints for managing book series in your personal library.")
public class SeriesController {

    private final SeriesService seriesService;

    @Operation(
            summary = "Get all series",
            description = "Retrieve a list of all book series in the library.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Series list retrieved successfully")
            }
    )
    @GetMapping
    public List<SeriesResponseDto> getAll() {
        return seriesService.getAllSeries();
    }

    @Operation(
            summary = "Add a new book series",
            description = "Create a new book series entry.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Details of the series to add",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = SeriesRequestDto.class),
                            examples = @ExampleObject(
                                    name = "Add new series example",
                                    value = """
                                            {
                                              "name": "Bridgerton series"
                                            }
                                            """
                            )
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Series created successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
            }
    )
    @PostMapping
    public SeriesResponseDto add(@RequestBody SeriesRequestDto dto) {
        return seriesService.addSeries(dto);
    }

    @Operation(
            summary = "Update a book series",
            description = "Update details of an existing book series by its ID.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Updated details of the series",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = SeriesRequestDto.class),
                            examples = @ExampleObject(
                                    name = "Update series example",
                                    value = """
                                            {
                                              "name": "The Witcher Saga"
                                            }
                                            """
                            )
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Series updated successfully"),
                    @ApiResponse(responseCode = "404", description = "Series not found", content = @Content)
            }
    )
    @PutMapping("/{id}")
    public SeriesResponseDto update(@PathVariable Long id, @RequestBody SeriesRequestDto dto) {
        return seriesService.updateSeries(id, dto);
    }

    @Operation(
            summary = "Delete a book series",
            description = "Remove a book series from the library by its ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Series deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "Series not found", content = @Content)
            }
    )
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        seriesService.deleteSeries(id);
    }
}
