package com.book.notebook.service;

import com.book.notebook.entity.Author;
import com.book.notebook.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service linked to Author Repository
 */
@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    /**
     * Get author by his id
     * @param id author id
     * @return a specific author
     */
    public Author getAuthorById(Long id){
        return authorRepository.findById(id).orElse(null);
    }

    /**
     * Get all unique authors presents in database
     * @return list of unique authors
     */
    public List<Author> getAllUniqueAuthors(){
        return authorRepository.findAll();
    }

    /**
     * Save a new Author Entity in database
     * @param fullName author name
     * @return new Author saved
     */
    public Author createAuthor(String fullName) {
        Author newAuthor = new Author();
        newAuthor.setFullname(fullName);

        return authorRepository.save(newAuthor);
    }
}
