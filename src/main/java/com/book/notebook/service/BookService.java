package com.book.notebook.service;

import com.book.notebook.entity.*;
import com.book.notebook.model.ReadingDetail;
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

    // ----------------------------- DATABASE ------------------------------ //

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

    /**
     * Save a new Book Entity in database
     * @param readingDetail reading informations
     * @return new Book saved
     */
    public Book createBook(ReadingDetail readingDetail) {
        Book newBook = new Book();
        newBook.setAuthorId(readingDetail.getIdAuthor());
        newBook.setTitle(readingDetail.getTitle());
        newBook.setCover(readingDetail.getCover());
        newBook.setSynopsis(readingDetail.getSynopsis());
        newBook.setSaga(readingDetail.isSaga());
        newBook.setAllTomePublished(readingDetail.isAllTomePublished());
        newBook.setNumberOfTome(readingDetail.getNumberOfTome());

        return bookRepository.save(newBook);
    }


}
