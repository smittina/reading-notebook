package com.book.notebook.service;

import com.book.notebook.entity.Genre;
import com.book.notebook.repository.GenreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class GenreServiceTest {

    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    private GenreService serviceTest;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
   void getAllByIdBookTest() {
        // given
        Genre genre1 = new Genre(1L, "Genre1", 1L);
        Genre genre2 = new Genre(2L, "Genre2", 2L);
        Genre genre3 = new Genre(3L, "Genre3", 3L);

        List<Genre> genres = Arrays.asList(genre1, genre2, genre3);
        doReturn(genres).when(genreRepository).findAllByBookId(anyLong());

        // when
        List<Genre> result = serviceTest.getAllByIdBook(1L);

        // then
        assertEquals(3, result.size());
        if(result.size() == 3) {
            assertEquals(genre1, result.get(0));
            assertEquals(genre2, result.get(1));
            assertEquals(genre3, result.get(2));
        }
    }

    @Test
    void getAllUniqueGenresTest() {
        // given
        Genre genre1 = new Genre(1L, "Genre1", 1L);
        Genre genre2 = new Genre(2L, "Genre1", 2L);
        Genre genre3 = new Genre(3L, "Genre2", 2L);
        Genre genre4 = new Genre(4L, "Genre3", 1L);
        Genre genre5 = new Genre(5L, "Genre3", 3L);

        List<Genre> genres = Arrays.asList(genre1, genre2, genre3, genre4, genre5);
        doReturn(genres).when(genreRepository).findAll();

        List<String> expected = Arrays.asList("Genre1", "Genre2", "Genre3");

        // when
        List<String> result = serviceTest.getAllUniqueGenres();

        // then
        assertEquals(expected.size(), result.size());
        if(result.size() == 3) {
            assertEquals(expected.get(0), result.get(0));
            assertEquals(expected.get(1), result.get(1));
            assertEquals(expected.get(2), result.get(2));
        }
    }

    @Test
    void createNewGenreTest() {
        // given
        List<String> genreList = Arrays.asList("Genre 1", "Genre 2", "Genre 3");

        Genre genre1 = new Genre(1L, "Genre1", 1L);
        Genre genre2 = new Genre(2L, "Genre2", 1L);
        Genre genre3 = new Genre(3L, "Genre3", 1L);

        List<Genre> expected = Arrays.asList(genre1, genre2, genre3);
        doReturn(expected).when(genreRepository).saveAll(anyList());

        // when
        List<Genre> result = serviceTest.createNewGenres(genreList, 1L);

        // then
        assertEquals(expected, result);
    }
}
