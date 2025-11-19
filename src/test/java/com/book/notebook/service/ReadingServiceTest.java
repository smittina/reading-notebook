package com.book.notebook.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

import com.book.notebook.entity.*;
import com.book.notebook.model.FormInformation;
import com.book.notebook.model.ReadingDetail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.book.notebook.enumeration.StatusOfReading;
import com.book.notebook.enumeration.TypeOfReading;
import com.book.notebook.repository.ReadingRepository;

@ExtendWith(MockitoExtension.class)
public class ReadingServiceTest {

    List<Reading> readings;
    List<Reading> readingsByYear;
    Book book = null;
    Author author = null;
    List<Genre> genres;
    List<Trope> tropes;
    List<Quotation> quotations;

    @Mock
    private ReadingRepository readingRepository;
    @Mock
    private BookService bookService;
    @Mock
    private AuthorService authorService;
    @Mock
    private GenreService genreService;
    @Mock
    private TropeService tropeService;
    @Mock
    private QuotationService quotationService;

    @InjectMocks
    private ReadingService serviceTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Readings
        Reading reading1 = new Reading(
                1L,
                1L,
                2023,
                9,
                LocalDateTime.of(2023, Month.SEPTEMBER, 25, 0, 0, 0),
                LocalDateTime.of(2023, Month.SEPTEMBER, 27, 0, 0, 0),
                StatusOfReading.FINISHED,
                TypeOfReading.HARDBACK,
                408,
                408);

        Reading reading2 = new Reading(
                2L,
                2L,
                2023,
                10,
                LocalDateTime.of(2023, Month.OCTOBER, 1, 0, 0, 0),
                LocalDateTime.of(2023, Month.OCTOBER, 3, 0, 0, 0),
                StatusOfReading.FINISHED,
                TypeOfReading.HARDBACK,
                456,
                456);

        Reading reading3 = new Reading(
                3L,
                3L,
                2024,
                3,
                LocalDateTime.of(2024, Month.MARCH, 1, 0, 0, 0),
                LocalDateTime.of(2024, Month.MARCH, 3, 0, 0, 0),
                StatusOfReading.FINISHED,
                TypeOfReading.HARDBACK,
                552,
                552);

        Reading reading4 = new Reading(
                4L,
                4L,
                2025,
                1,
                LocalDateTime.of(2025, Month.JANUARY, 1, 0, 0, 0),
                LocalDateTime.of(2025, Month.JANUARY, 3, 0, 0, 0),
                StatusOfReading.FINISHED,
                TypeOfReading.HARDBACK,
                456,
                456);

        readings = new ArrayList<>(Arrays.asList(reading1, reading2, reading3, reading4));
        readingsByYear= new ArrayList<>(Arrays.asList(reading1, reading2));

        // BookDetail
        book = new Book(
                1L,
                1L,
                "Le Pont des Tempêtes",
                null,
                "synopsis",
                true,
                false,
                1
        );
        author = new Author(
                1L,
                "Danielle L. Jensen"
        );
        Genre genre1 = new Genre(
                1L,
                "Fantasy",
                1L
        );
        Genre genre2 = new Genre(
                2L,
                "Romantasy",
                1L
        );
        Trope trope1 = new Trope(
                1L,
                "Mariage arrangé",
                1L
        );
        Trope trope2 = new Trope(
                2L,
                "Ennemies To Lovers",
                1L
        );
        Quotation quotation1 = new Quotation(
                1L,
                "À présent chaque victoire, chaque défaite, chaque caresse ou chaque bataille… tout cela n’appartiendrait qu’à elle. Elle serait maîtresse de son destin, comme elle était maîtresse de cet instant.",
                1L
        );
        genres = new ArrayList<>(Arrays.asList(genre1, genre2));
        tropes = new ArrayList<>(Arrays.asList(trope1, trope2));
        quotations = new ArrayList<>();
        quotations.add(quotation1);
    }

    @Test
    public void getAllYearsOfReadingsTest() {
        // given
        Map<Integer,Long> expected = new HashMap<>();
        expected.put(2023, 2L);
        expected.put(2024, 1L);
        expected.put(2025, 1L);
        
        doReturn(readings).when(readingRepository).findAll();

        // when
        Map<Integer, Long> result = serviceTest.getAllYearsOfReadings();

        // then
        assertEquals(expected.size(), result.size());
        if (result.size() == 3) {
            assertEquals(expected.get(1), result.get(1));
            assertEquals(expected.get(2), result.get(2));
            assertEquals(expected.get(3), result.get(3));
        }
    }

    @Test
    public void getAllYearsOfReadingsWhenNoReadingsFoundTest() {
        // given
        doReturn(null).when(readingRepository).findAll();

        // when
        Map<Integer, Long> result = serviceTest.getAllYearsOfReadings();

        // then
        assertEquals(0, result.size());
    }

    @Test
    public void getAllMonthsOfReadingsByYearTest() {
        // given
        Map<Integer,Long> expected = new HashMap<>();
        expected.put(1, 0L);
        expected.put(2, 0L);
        expected.put(3, 0L);
        expected.put(4, 0L);
        expected.put(5, 0L);
        expected.put(6, 0L);
        expected.put(7, 0L);
        expected.put(8, 0L);
        expected.put(9, 1L);
        expected.put(10, 1L);
        expected.put(11, 0L);
        expected.put(12, 0L);

        doReturn(readingsByYear).when(readingRepository).findAllByYearOfReading(anyInt());

        // when
        Map<Integer,Long> result = serviceTest.getAllMonthsOfReadingsByYear(2023);

        // then
        assertEquals(expected.size(), result.size());
         if (result.size() == 12) {
            assertEquals(expected.get(1), result.get(1));
            assertEquals(expected.get(2), result.get(2));
            assertEquals(expected.get(3), result.get(3));
            assertEquals(expected.get(4), result.get(4));
            assertEquals(expected.get(5), result.get(5));
            assertEquals(expected.get(6), result.get(6));
            assertEquals(expected.get(7), result.get(7));
            assertEquals(expected.get(8), result.get(8));
            assertEquals(expected.get(9), result.get(9));
            assertEquals(expected.get(10), result.get(10));
            assertEquals(expected.get(11), result.get(11));
            assertEquals(expected.get(12), result.get(12));
        }
    }

    @Test
    public void getAllMonthsOfReadingsByYearWhenNoReadingsFoundTest() {
        // given
        Map<Integer,Long> expected = new HashMap<>();
        expected.put(1, 0L);
        expected.put(2, 0L);
        expected.put(3, 0L);
        expected.put(4, 0L);
        expected.put(5, 0L);
        expected.put(6, 0L);
        expected.put(7, 0L);
        expected.put(8, 0L);
        expected.put(9, 0L);
        expected.put(10, 0L);
        expected.put(11, 0L);
        expected.put(12, 0L);

        doReturn(null).when(readingRepository).findAllByYearOfReading(anyInt());

        // when
        Map<Integer,Long> result = serviceTest.getAllMonthsOfReadingsByYear(2023);

        // then
        assertEquals(expected.size(), result.size());
        if (result.size() == 12) {
            assertEquals(expected.get(1), result.get(1));
            assertEquals(expected.get(2), result.get(2));
            assertEquals(expected.get(3), result.get(3));
            assertEquals(expected.get(4), result.get(4));
            assertEquals(expected.get(5), result.get(5));
            assertEquals(expected.get(6), result.get(6));
            assertEquals(expected.get(7), result.get(7));
            assertEquals(expected.get(8), result.get(8));
            assertEquals(expected.get(9), result.get(9));
            assertEquals(expected.get(10), result.get(10));
            assertEquals(expected.get(11), result.get(11));
            assertEquals(expected.get(12), result.get(12));
        }
    }

    @Test
    public void getReadingDetailsTest() {
        // given
        ReadingDetail expected = new ReadingDetail(
                readings.getFirst(),
                book,
                author,
                genres,
                tropes,
                quotations
        );
        Optional<Reading> readingOptional = Optional.of(readings.getFirst());

        doReturn(readingOptional).when(readingRepository).findById(anyLong());
        doReturn(book).when(bookService).getBookById(anyLong());
        doReturn(author).when(authorService).getAuthorById(anyLong());
        doReturn(genres).when(genreService).getAllByIdBook(anyLong());
        doReturn(tropes).when(tropeService).getTropeByBookId(anyLong());
        doReturn(quotations).when(quotationService).getAllByBookId(anyLong());

        // when
        ReadingDetail result = serviceTest.getReadingDetail(1L);

        // then
        assertEquals(expected.getTitle(), result.getTitle());
        assertEquals(expected.getAuthorName(), result.getAuthorName());
        assertEquals(expected.getCover(), result.getCover());
        assertEquals(expected.getSynopsis(), result.getSynopsis());
        assertEquals(expected.getGenres().size(), result.getGenres().size());
        if (result.getGenres().size() ==2) {
            assertEquals(expected.getGenres().get(0), result.getGenres().get(0));
            assertEquals(expected.getGenres().get(1), result.getGenres().get(1));
        }
        assertEquals(expected.getTropes().size(), result.getTropes().size());
        if (result.getTropes().size() == 2) {
            assertEquals(expected.getTropes().get(0), result.getTropes().get(0));
            assertEquals(expected.getTropes().get(1), result.getTropes().get(1));
        }
        assertEquals(expected.getPageNumber(), result.getPageNumber());
        assertEquals(expected.getNumberOfTome(), result.getNumberOfTome());
        assertEquals(expected.getStatus(), result.getStatus());
        assertEquals(expected.getTypeOfReading(), result.getTypeOfReading());
        assertEquals(expected.getStarting(), result.getStarting());
        assertEquals(expected.getFinished(), result.getFinished());
        assertEquals(expected.getQuotations().size(), result.getQuotations().size());
        if (!result.getQuotations().isEmpty()) {
            assertEquals(expected.getQuotations().getFirst(), result.getQuotations().getFirst());
        }
        assertEquals(expected.getCurrentPage(), result.getCurrentPage());
    }

    @Test
    public void getReadingDetailsWhenReadingIsNullTest() {
        // given
        doReturn(Optional.empty()).when(readingRepository).findById(anyLong());

        // when
        ReadingDetail result = serviceTest.getReadingDetail(1L);

        // then
        assertNull(result);
    }

    @Test
    public void getFormInformationTest() {
        // given
        Book book1 = new Book();
        book1.setId(1L);
        book1.setTitle("Book 1");

        Book book2 = new Book();
        book2.setId(2L);
        book2.setTitle("Book 2");

        Book book3 = new Book();
        book3.setId(3L);
        book3.setTitle("Book 3");

        List<Book> books = Arrays.asList(book1, book2, book3);

        Author author1 = new Author(1L, "Author 1");
        Author author2 = new Author(2L, "Author 2");
        Author author3 = new Author(3L, "Author 3");
        Author  author4 = new Author(4L, "Author 4");

        List<Author> authors = Arrays.asList(author1, author2, author3, author4);

        List<String> genres = Arrays.asList("Genre 1", "Genre 2", "Genre 3", "Genre 4", "Genre 5");
        List<String> tropes = Arrays.asList("Trope 1", "Trope 2", "Trope 3");

        doReturn(books).when(bookService).getAllUniqueBooks();
        doReturn(authors).when(authorService).getAllUniqueAuthors();
        doReturn(genres).when(genreService).getAllUniqueGenres();
        doReturn(tropes).when(tropeService).getAllUniqueTropes();

        // when
        FormInformation result = serviceTest.getFormInformation();

        // then
        assertNotNull(result.getBooks());
        assertNotNull(result.getAuthors());
        assertNotNull(result.getGenres());
        assertNotNull(result.getTropes());

        assertEquals(3, result.getBooks().size());
        assertEquals(4, result.getAuthors().size());
        assertEquals(5, result.getGenres().size());
        assertEquals(3, result.getTropes().size());

    }
}
