package com.book.notebook.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.book.notebook.entity.Reading;
import com.book.notebook.enumeration.StatusOfReading;
import com.book.notebook.enumeration.TypeOfReading;
import com.book.notebook.repository.ReadingRepository;

@ExtendWith(MockitoExtension.class)
public class ReadingServiceTest {

    private static final List<Reading> readings = new ArrayList<>();
    private static final List<Reading> readingsByYear = new ArrayList<>();

    @Mock
    private ReadingRepository readingRepository;

    @InjectMocks
    private ReadingService serviceTest;

    @BeforeAll
    static void init() {
        
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
        
        readings.addAll(Arrays.asList(reading1, reading2, reading3, reading4));
        readingsByYear.addAll(Arrays.asList(reading1, reading2));
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
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
        Map<Integer,Long> expected = new HashMap<>();

        doReturn(null).when(readingRepository).findAll();

        // when
        Map<Integer, Long> result = serviceTest.getAllYearsOfReadings();

        // then
        assertEquals(expected.size(), result.size());
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
}
