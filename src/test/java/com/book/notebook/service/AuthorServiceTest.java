package com.book.notebook.service;

import com.book.notebook.entity.Author;
import com.book.notebook.repository.AuthorRepository;
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
public class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService serviceTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAuthorByIdTest(){
        // given
        Author author = new Author(1L, "Name");
        Author expected = new Author(1L, "Name");

        Optional<Author> authorOptional =  Optional.of(author);

        doReturn(authorOptional).when(authorRepository).findById(anyLong());

        // when
        Author result = serviceTest.getAuthorById(1L);

        // then
        assertInstanceOf(Author.class, result);
        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getFullname(), result.getFullname());

    }

    @Test
    public void getAuthorByIdWhenOptionIsNotPresentTest(){
        // given
        Optional<Author> authorOptional =  Optional.empty();

        doReturn(authorOptional).when(authorRepository).findById(anyLong());

        // when
        Author result = serviceTest.getAuthorById(1L);

        // then
        assertNull(result);
    }

    @Test
    public void getAllUniqueAuthorsTest(){
        // given
        Author author1 = new Author(1L, "Name1");
        Author author2 = new Author(2L, "Name2");
        Author author3 = new Author(3L, "Name3");

        List<Author> authorList = Arrays.asList(author1, author2, author3);
        List<Author> expected = Arrays.asList(author1, author2, author3);

        doReturn(authorList).when(authorRepository).findAll();

        // when
        List<Author> result = serviceTest.getAllUniqueAuthors();

        // then
        assertInstanceOf(List.class, result);
        assertEquals(expected.size(), result.size());
        if(result.size() == 3) {
            assertEquals(expected.get(0).getId(), result.get(0).getId());
            assertEquals(expected.get(1).getId(), result.get(1).getId());
            assertEquals(expected.get(2).getId(), result.get(2).getId());
            assertEquals(expected.get(0).getFullname(), result.get(0).getFullname());
            assertEquals(expected.get(1).getFullname(), result.get(1).getFullname());
            assertEquals(expected.get(2).getFullname(), result.get(2).getFullname());
        }
    }
}
