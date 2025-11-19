package com.book.notebook.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Repository;

import com.book.notebook.entity.Reading;

/**
 * Reading Repository
 */
@Repository
public interface ReadingRepository extends JpaRepository<Reading, Long> {

    Optional<Reading> findById(Long id);

    List<Reading> findAllByYearOfReading(int yearOfReading);

    List<Reading> findAllByYearOfReadingAndMonthOfReading(int yearOfReading, int monthOfReading);

}
