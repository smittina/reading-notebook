package com.book.notebook.service;

import com.book.notebook.entity.Book;
import com.book.notebook.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService serviceTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getBookByIdTest(){
        // given
        Book book = new Book();
        book.setId(1L);
        book.setTitle("title");

        Book expected = new Book();
        expected.setId(1L);
        expected.setTitle("title");

        Optional<Book> bookOptional = Optional.of(book);

        doReturn(bookOptional).when(bookRepository).findById(anyLong());

        // when
        Book result = serviceTest.getBookById(1L);

        // then
        assertInstanceOf(Book.class, result);
        assertEquals(expected.getTitle(), result.getTitle());
        assertEquals(expected.getId(), result.getId());
    }

    @Test
    public void getBookByIdWhenOptionalIsNotPresentTest(){
        // given
        Optional<Book> bookOptional = Optional.empty();

        // when
        Book result = serviceTest.getBookById(1L);

        // then
        assertNull(result);
    }

    @Test
    public void getAllUniqueBooksTest(){
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

        List<Book> bookList = Arrays.asList(book1, book2, book3);

        List<Book> expected = Arrays.asList(book1, book2, book3);

        doReturn(bookList).when(bookRepository).findAll();

        // when
        List<Book> result = serviceTest.getAllUniqueBooks();

        // then
        assertInstanceOf(List.class, result);
        assertEquals(expected.size(), result.size());
        if(result.size() == 3) {
            assertEquals(expected.get(0).getId(), result.get(0).getId());
            assertEquals(expected.get(1).getId(), result.get(1).getId());
            assertEquals(expected.get(2).getId(), result.get(2).getId());

            assertEquals(expected.get(0).getTitle(), result.get(0).getTitle());
            assertEquals(expected.get(1).getTitle(), result.get(1).getTitle());
            assertEquals(expected.get(2).getTitle(), result.get(2).getTitle());
        }
    }
}
