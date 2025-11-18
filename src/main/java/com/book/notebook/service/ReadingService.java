package com.book.notebook.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.notebook.constant.ReadingConstant;
import com.book.notebook.entity.Reading;
import com.book.notebook.repository.ReadingRepository;

@Service
public class ReadingService {

    @Autowired
    private ReadingRepository readingRepository;

    public List<Reading> getAll() {
        return readingRepository.findAll();
    }

    public Map<Integer, Long> getAllYearsOfReadings() {
        Map<Integer, Long> readingsByYears = new HashMap<>();
        List<Reading> readings = getAll();
        List<Integer> uniqueAllYearsOfReading = new ArrayList<>();
        if (readings != null) {
            uniqueAllYearsOfReading = readings.stream()
                .map(Reading::getYearOfReading)
                .distinct()
                .collect(Collectors.toList());    
                   
            uniqueAllYearsOfReading.stream().forEach(y -> {
                long numberOfReading = readings.stream()
                    .filter(e -> e.getYearOfReading() == y)
                    .count();
                readingsByYears.put(y, numberOfReading);
            });       
        }
        return readingsByYears;
    }

    public Map<Integer, Long> getAllMonthsOfReadingsByYear(int year) {
        Map<Integer, Long> readingsByMonths = initializeMonthsOfReadings();
        List<Reading> readingsOfYear = getAllByYearOfReading(year);
        List<Integer> uniqueAllMonthsOfReading = new ArrayList<>();
        if (readingsOfYear != null) {
            uniqueAllMonthsOfReading = readingsOfYear.stream()
            .map(Reading::getMonthOfReading)
            .distinct()
            .collect(Collectors.toList());

            uniqueAllMonthsOfReading.stream().forEach(m -> {
                long numberOfReading = readingsOfYear.stream()
                .filter(e -> e.getMonthOfReading() == m)
                .count();

                readingsByMonths.put(m, numberOfReading);
            });
        }
        return readingsByMonths;
    }

    private Map<Integer, Long> initializeMonthsOfReadings() {
        Map<Integer, Long> months = new HashMap<>();
        for(int i = 0; i <12; i++) {
            months.put(i+1, ReadingConstant.INIT_MONTH);
        }
        return months;
    }

    public List<Reading> getAllByYearOfReading(int year) {
        List<Reading> readings = readingRepository.findAllByYearOfReading(year);
        return readings;
    }

    public List<Reading> getAllByMonthOfReading(int month) {
        List<Reading> readings = readingRepository.findAllByMonthOfReading(month);
        return readings;
    }

}
