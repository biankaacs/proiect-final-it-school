package com.example.HomeLibrary.repo;

import com.example.HomeLibrary.model.entities.Series;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeriesRepository extends JpaRepository<Series, Long> {
}
