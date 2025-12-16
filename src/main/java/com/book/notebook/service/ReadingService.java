package com.book.notebook.service;

import java.util.*;

import com.book.notebook.entity.*;
import com.book.notebook.enumeration.StatusOfReading;
import com.book.notebook.enumeration.TypeOfReading;
import com.book.notebook.mapper.ReadingConfigurationMapper;
import com.book.notebook.model.BookResume;
import com.book.notebook.model.FormInformation;
import com.book.notebook.model.ReadingDetail;
import com.book.notebook.model.ReadingResume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.notebook.constant.ReadingConstant;
import com.book.notebook.repository.ReadingRepository;

/**
 * Service linked to Reading Repository and other services
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
    @Autowired
    private GenreListService genreListService;
    @Autowired
    private TropeListService tropeListService;

    // ------------------------------- DATABASE OPERATIONS --------------------------------- //

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

    public List<ReadingResume> getAllReadingResumes() {

        List<ReadingResume> readingResumes = new ArrayList<>();

        List<Integer> years = readingRepository.findAllYearOfReading();
        years.forEach(year -> {
            List<Reading> readings = getAllByYearOfReading(year);
            List<BookResume> bookResumes = new ArrayList<>();
            readings.forEach(reading -> {
                Book book = bookService.getBookById(reading.getBookId());
                Author author = authorService.getAuthorById(book.getAuthorId());
                bookResumes.add(new BookResume(reading.getId(), book.getId(), author.getId(), book.getTitle(), author.getFullname(), reading.getRating()));
            });
            readingResumes.add(new ReadingResume(year, bookResumes));
        });

        return readingResumes;
    }

    /**
     * Get all book resumes for a specific year
     * @param year year selectged
     * @return list of all book resumes for a specific year
     */
    public List<BookResume> getAllBookResumesByYear(int year) {
        List<Reading> readings = getAllByYearOfReading(year);
        List<BookResume> bookResumes = new ArrayList<>();
        readings.forEach(reading -> {
            Book book = bookService.getBookById(reading.getBookId());
            Author author = authorService.getAuthorById(book.getAuthorId());
            bookResumes.add(new BookResume(reading.getId(), book.getId(), author.getId(), book.getTitle(), author.getFullname(), reading.getRating()));
        });
        return bookResumes;
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

    // -------------------------------------- READING DETAILS ------------------------------------ //

    /**
     * Get Reading detail about a specific book
     * @param readingId reading id in database
     * @return reading detail contains information about book, author, genres, tropes and quotations
     */
    public ReadingDetail getReadingDetail(Long readingId) {
        // Get Reading
            Optional<Reading> optionalReading = readingRepository.findById(readingId);
        if (optionalReading.isPresent()) {
            // Get Book
            Book book = bookService.getBookById(optionalReading.get().getBookId());
            // Get Author
            Author author = authorService.getAuthorById(book.getAuthorId());
            // Get Genre
            List<Genre> genres = genreService.getAllByIdBook(optionalReading.get().getBookId());
            // Get Trope
            List<Trope> tropes = tropeService.getTropeByBookId(optionalReading.get().getBookId());
            // Get Quotation
            List<Quotation> quotations = quotationService.getAllByBookId(optionalReading.get().getBookId());

            // Create BookDetails
            return new ReadingDetail(optionalReading.get(), book, author, genres, tropes, quotations);
        }
        return null;
    }

    // --------------------------------------- FORM INFORMATIONS -------------------------------------------- //

    /**
     * Get all informations necessary to create new reading or update an existent reading
     * @return form informations
     */
    public FormInformation getFormInformation() {
        // Get all unique books
        List<Book> books = bookService.getAllUniqueBooks();
        // Get all unique authors
        List<Author> authors = authorService.getAllUniqueAuthors();
        // Get list of unique genres
        List<String> genres = ReadingConfigurationMapper.convertGenreListToNameList(genreListService.getAll());
        // Get list of unique tropes
        List<String> tropes = ReadingConfigurationMapper.convertTropeListToNameList(tropeListService.getAll());

        return new FormInformation(books, authors, genres, tropes);
    }

    // ----------------------- CREATE AND UPDATE ENTRANCE IN REPOSITORIES ------------------------------------ //

    /**
     * Insert new Reading Entity in database
     * @param readingDetail data relative to new reading
     * @return new reading Entity
     */
    public Reading createNewReading(ReadingDetail readingDetail) {
        boolean isNewReading = readingDetail.getIdAuthor() == 0 && readingDetail.getIdBook() == 0;
        if(isNewReading) {
            // AUTHOR
            Author newAuthor = authorService.createAuthor(readingDetail.getAuthorName());
            readingDetail.setIdAuthor(newAuthor.getId());
            // BOOK
            Book newBook = bookService.createBook(readingDetail);
            readingDetail.setIdBook(newBook.getId());
            // GENRE
            genreService.createNewGenres(readingDetail.getGenres(), readingDetail.getIdBook());
            // TROPE
            tropeService.createNewTropes(readingDetail.getTropes(), readingDetail.getIdBook());
        }
        // QUOTATION
        quotationService.createNewQuotations(readingDetail.getQuotations(), readingDetail.getIdBook());
        // READING
        Reading newReading = constructReading(readingDetail);
        return readingRepository.save(newReading);
    }

    // --------------------------------- OPERATIONS --------------------------------- //

    /**
     * Construct new Reading Entity
     * @param detail reading informations necessary to construct the entity
     * @return the new Reading Entity
     */
    public Reading constructReading(ReadingDetail detail) {
        Reading newReading = new Reading();
        newReading.setBookId(detail.getIdBook());
        if(detail.getFinished() != null) {
            newReading.setYearOfReading(detail.getFinished().getYear());
            newReading.setMonthOfReading(detail.getFinished().getMonth().getValue());
        }
        newReading.setStarting(detail.getStarting());
        newReading.setFinished(detail.getFinished());
        newReading.setStatusOfReading(StatusOfReading.getValue(detail.getStatus()));
        newReading.setTypeOfReading(TypeOfReading.getValue(detail.getTypeOfReading()));
        newReading.setPageNumber(detail.getPageNumber());
        newReading.setCurrentPage(detail.getCurrentPage());
        newReading.setRating(detail.getRating());

        return  newReading;
    }


}
