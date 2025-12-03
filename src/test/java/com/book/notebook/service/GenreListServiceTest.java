package com.book.notebook.service;

import com.book.notebook.entity.GenreList;
import com.book.notebook.repository.GenreListRepository;
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
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class GenreListServiceTest {

    @Mock
    private GenreListRepository genreListRepository;

    @InjectMocks
    private GenreListService serviceTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllTest() {
        // given
        List<GenreList> list = Arrays.asList(new GenreList(), new GenreList());

        doReturn(list).when(genreListRepository).findAll();

        // when
        List<GenreList> result = serviceTest.getAll();

        // then
        assertEquals(list, result);
    }

    @Test
    void updateGenreListTest() {
        // given
        List<GenreList> list = Arrays.asList(new GenreList(1L,"genre1"), new GenreList(2L, "genre2"));

        doReturn(list).when(genreListRepository).saveAll(anyList());

        // when
        List<GenreList> result = serviceTest.updateGenreList(Arrays.asList("genre1", "genre2"));

        // then
        verify(genreListRepository).saveAll(anyList());
        verify(genreListRepository).deleteAll();
        assertEquals(list, result);

    }
}
