package com.book.notebook.service;

import com.book.notebook.entity.Trope;
import com.book.notebook.repository.TropeRepository;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class TropeServiceTest {

    @Mock
    private TropeRepository tropeRepository;

    @InjectMocks
    private TropeService serviceTest;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getTropeByBookIdTest(){
        // given
        Trope trope1 = new Trope(1L, "Trope1", 1L);
        Trope trope2 = new Trope(2L, "Trope2", 2L);
        Trope trope3 = new Trope(3L, "Trope3", 3L);

        List<Trope> tropes = Arrays.asList(trope1, trope2, trope3);
        doReturn(tropes).when(tropeRepository).findByBookId(anyLong());

        // when
        List<Trope> result = serviceTest.getTropeByBookId(1L);

        // then
        assertEquals(3, result.size());
        if(result.size() == 3) {
            assertEquals(trope1, result.get(0));
            assertEquals(trope2, result.get(1));
            assertEquals(trope3, result.get(2));
        }
    }

    @Test
    public void getAllUniqueTropeTest(){
        // given
        Trope trope1 = new Trope(1L, "Trope1", 1L);
        Trope trope2 = new Trope(2L, "Trope1", 2L);
        Trope trope3 = new Trope(3L, "Trope2", 2L);
        Trope trope4 = new Trope(4L, "Trope3", 3L);
        Trope trope5 = new Trope(5L, "Trope4", 3L);

        List<Trope> tropes = Arrays.asList(trope1, trope2, trope3, trope4, trope5);
        doReturn(tropes).when(tropeRepository).findAll();

        List<String> expected = Arrays.asList("Trope1", "Trope2", "Trope3", "Trope4");

        // when
        List<String> result = serviceTest.getAllUniqueTropes();

        // then
        assertEquals(expected.size(), result.size());
        if(result.size() == 4) {
            assertEquals(expected.get(0), result.get(0));
            assertEquals(expected.get(1), result.get(1));
            assertEquals(expected.get(2), result.get(2));
            assertEquals(expected.get(3), result.get(3));
        }
    }
}
