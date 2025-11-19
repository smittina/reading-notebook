package com.book.notebook.service;

import com.book.notebook.entity.*;
import com.book.notebook.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service linked to Book Repository
 */
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    /**
     * Get book by his id
     * @param id book id
     * @return a specific book
     */
    public Book getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElse(null);
    }

    /**
     * Get all unique books presents in database
     * @return list of unique books
     */
    public List<Book> getAllUniqueBooks() {
        return bookRepository.findAll();
    }

}
