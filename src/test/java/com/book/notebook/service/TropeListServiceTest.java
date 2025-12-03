package com.book.notebook.service;

import com.book.notebook.entity.GenreList;
import com.book.notebook.entity.TropeList;
import com.book.notebook.repository.GenreListRepository;
import com.book.notebook.repository.TropeListRepository;
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
public class TropeListServiceTest {

    @Mock
    private TropeListRepository tropeListRepository;

    @InjectMocks
    private TropeListService serviceTest;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllTest() {
        // given
        List<TropeList> list = Arrays.asList(new TropeList(), new TropeList());
        doReturn(list).when(tropeListRepository).findAll();

        // when
        List<TropeList> result = serviceTest.getAll();

        // then
        assertEquals(list, result);
    }

    @Test
    void updateTropeListTest() {
        // given
        List<TropeList> list = Arrays.asList(new TropeList(1L, "trope1"), new TropeList(2L, "trope2"));

        doReturn(list).when(tropeListRepository).saveAll(anyList());

        // when
        List<TropeList> result = serviceTest.updateTropeList(Arrays.asList("trope1", "trope2"));

        // then
        verify(tropeListRepository).saveAll(anyList());
        verify(tropeListRepository).deleteAll();
        assertEquals(list, result);
    }

}
