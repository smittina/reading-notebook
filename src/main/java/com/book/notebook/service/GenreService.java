package com.book.notebook.service;

import com.book.notebook.entity.Genre;
import com.book.notebook.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service linked to GenreRepository
 */
@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    /**
     * Get all genres for a book id
     * @param bookId book id
     * @return all genres assigned for a specific book
     */
    public List<Genre> getAllByIdBook(Long bookId) {
        return genreRepository.findAllByBookId(bookId);
    }

    /**
     * Get all unique genre titles in database
     * @return list of all unique genre titles
     */
    public List<String> getAllUniqueGenres() {
        return genreRepository.findAll()
                .stream()
                .map(Genre::getTitle)
                .distinct()
                .toList();
    }
}
