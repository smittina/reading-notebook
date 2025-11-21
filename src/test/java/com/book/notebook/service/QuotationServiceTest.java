package com.book.notebook.service;

import com.book.notebook.entity.Quotation;
import com.book.notebook.entity.Trope;
import com.book.notebook.repository.QuotationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class QuotationServiceTest {

    @Mock
    private QuotationRepository quotationRepository;

    @InjectMocks
    private QuotationService serviceTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllByBookIdTest() {
        // given
        Quotation quotation1 = new Quotation(1L, "Quotation 1", 1L);
        Quotation quotation2 = new Quotation(2L, "Quotation 2", 1L);

        doReturn(Arrays.asList(quotation1, quotation2)).when(quotationRepository).findAllByBookId(anyLong());

        // when
        List<Quotation> result = serviceTest.getAllByBookId(1L);

        // then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(quotation1, result.get(0));
        assertEquals(quotation2, result.get(1));
    }

    @Test
    void createNewQuotationTest() {
        // given
        List<String> quotations = Arrays.asList("Quotation 1", "Quotation 2");

        Quotation quotation = new Quotation(1L, "Quotation 1", 1L);
        Quotation quotation2 = new Quotation(2L, "Quotation 2", 1L);
        List<Quotation> expected = Arrays.asList(quotation, quotation2);

        doReturn(expected).when(quotationRepository).saveAll(anyList());

        // when
        List<Quotation> result = serviceTest.createNewQuotations(quotations, 1L);

        // then
        assertNotNull(result);
        assertEquals(expected, result);
    }
}
