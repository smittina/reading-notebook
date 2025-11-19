package com.book.notebook.service;

import java.util.*;

import com.book.notebook.entity.*;
import com.book.notebook.model.ReadingDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.notebook.constant.ReadingConstant;
import com.book.notebook.repository.ReadingRepository;

/**
 * Service linked to Reading Repository
 */
@Service
public class ReadingService {

    @Autowired
    private ReadingRepository readingRepository;

    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private TropeService tropeService;
    @Autowired
    private GenreService genreService;
    @Autowired
    private QuotationService quotationService;

    /**
     * Get all readings without worrying about year of reading
     * @return list of all readings
     */
    public List<Reading> getAll() {
        return readingRepository.findAll();
    }


    /**
     * Get all readings for a specific year
     * @param year year selected
     * @return list of all readings for a specific year
     */
    public List<Reading> getAllByYearOfReading(int year) {
        return readingRepository.findAllByYearOfReading(year);
    }

    /**
     * Get all readings for specific month and year
     * @param year year selected
     * @param month month selected
     * @return list of all readings for specific month and year
     */
    public List<Reading> getAllByMonthAndYearOfReading(int year, int month) {
        return readingRepository.findAllByYearOfReadingAndMonthOfReading(year, month);
    }

    /**
     * Get all readings by year of readings
     * @return map of reading's number by year of reading
     */
    public Map<Integer, Long> getAllYearsOfReadings() {
        Map<Integer, Long> readingsByYears = new HashMap<>();
        List<Reading> readings = getAll();
        List<Integer> uniqueAllYearsOfReading;
        if (readings != null) {
            uniqueAllYearsOfReading = readings.stream()
                .map(Reading::getYearOfReading)
                .distinct()
                .toList();
                   
            uniqueAllYearsOfReading.forEach(y -> {
                long numberOfReading = readings.stream()
                    .filter(e -> e.getYearOfReading() == y)
                    .count();
                readingsByYears.put(y, numberOfReading);
            });       
        }
        return readingsByYears;
    }

    /**
     * Get all readings by months of reading for a specific year
     * @param year year of reading
     * @return map of number of reading by month for a specific year
     */
    public Map<Integer, Long> getAllMonthsOfReadingsByYear(int year) {
        Map<Integer, Long> readingsByMonths = initializeMonthsOfReadings();
        List<Reading> readingsOfYear = getAllByYearOfReading(year);
        List<Integer> uniqueAllMonthsOfReading;
        if (readingsOfYear != null) {
            uniqueAllMonthsOfReading = readingsOfYear.stream()
            .map(Reading::getMonthOfReading)
            .distinct()
            .toList();

            uniqueAllMonthsOfReading.forEach(m -> {
                long numberOfReading = readingsOfYear.stream()
                .filter(e -> e.getMonthOfReading() == m)
                .count();

                readingsByMonths.put(m, numberOfReading);
            });
        }
        return readingsByMonths;
    }

    /**
     * Init readings to 0 for all months of a year
     * @return map with all months inits to 0 reading
     */
    private Map<Integer, Long> initializeMonthsOfReadings() {
        Map<Integer, Long> months = new HashMap<>();
        for(int i = 0; i <12; i++) {
            months.put(i+1, ReadingConstant.INIT_MONTH);
        }
        return months;
    }

    public ReadingDetail getReadingDetail(Long readingId) {
        // Get Reading
            Optional<Reading> optionalReading = readingRepository.findById(readingId);
        if (optionalReading.isPresent()) {
            // Get Book
            Book book = bookService.getBookById(readingId);
            // Get Author
            Author author = authorService.getById(book.getIdAuthor());
            // Get Genre
            List<Genre> genres = genreService.getAllByIdBook(optionalReading.get().getIdBook());
            // Get Trope
            List<Trope> tropes = tropeService.getTropeByBookId(optionalReading.get().getIdBook());
            // Get Quotation
            List<Quotation> quotations = quotationService.getAllByBookId(optionalReading.get().getIdBook());

            // Create BookDetails
            return new ReadingDetail(optionalReading.get(), book, author, genres, tropes, quotations);
        }

        return null;
    }
}
