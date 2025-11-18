package com.book.notebook.service;

import com.book.notebook.entity.Genre;
import com.book.notebook.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public List<Genre> getAllByIdBook(Long bookId) {
        return genreRepository.findAllByBookId(bookId);
    }
}
