package com.book.notebook.service;

import com.book.notebook.entity.GenreList;
import com.book.notebook.entity.TropeList;
import com.book.notebook.model.ReadingConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class ReadingConfigurationServiceTest {

    @Mock
    private GenreListService genreListService;
    @Mock
    private TropeListService tropeListService;

    @InjectMocks
    private ReadingConfigurationService serviceTest;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getReadingConfigurationTest() {
        // given
        List<GenreList> genreLists = Arrays.asList(
               new GenreList(1L, "genre1"),
               new GenreList(2L, "genre2"),
               new GenreList(3L, "genre3")
        );

        List<TropeList> tropeLists = Arrays.asList(
                new TropeList(1L, "trope1"),
                new TropeList(2L, "trope2"),
                new TropeList(3L, "trope3"),
                new TropeList(4L, "trope4")
        );

        ReadingConfiguration expected = new ReadingConfiguration(
                Arrays.asList("genre1", "genre2", "genre3"),
                Arrays.asList("trope1", "trope2", "trope3", "trope4")
        );

        doReturn(genreLists).when(genreListService).getAll();
        doReturn(tropeLists).when(tropeListService).getAll();

        // when
        ReadingConfiguration result = serviceTest.getReadingConfiguration();

        // then
        assertEquals(expected.getGenres().size(), result.getGenres().size());
        assertEquals(expected.getTropes().size(), result.getTropes().size());
    }

    @Test
    void updateReadingConfigurationTest() {
        // given
        ReadingConfiguration config = new ReadingConfiguration(
                Arrays.asList("genre1", "genre2"),
                Arrays.asList("trope1", "trope2", "trope3")
        );

        List<GenreList> genreLists = Arrays.asList(
                new GenreList(1L, "genre1"),
                new GenreList(2L, "genre2"),
                new GenreList(3L, "genre3")
        );

        List<TropeList> tropeLists = Arrays.asList(
                new TropeList(1L, "trope1"),
                new TropeList(2L, "trope2")
        );

        ReadingConfiguration expected = new ReadingConfiguration(
                Arrays.asList("genre1", "genre2", "genre3"),
                Arrays.asList("trope1", "trope2")
        );

        doReturn(genreLists).when(genreListService).updateGenreList(anyList());
        doReturn(tropeLists).when(tropeListService).updateTropeList(anyList());

        // when
        ReadingConfiguration result = serviceTest.updateReadingConfiguration(config);

        // then
        assertEquals(expected.getGenres().size(), result.getGenres().size());
        assertEquals(expected.getTropes().size(), result.getTropes().size());
    }
}
