package com.book.notebook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.book.notebook.entity.Reading;

@Repository
public interface ReadingRepository extends JpaRepository<Reading, Long> {

    List<Reading> findAllByYearOfReading(int yearOfReading);

    List<Reading> findAllByMonthOfReading(int monthOfReading);

}
