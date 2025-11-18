package com.book.notebook.service;

import com.book.notebook.entity.Author;
import com.book.notebook.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author getById(Long id){
        return authorRepository.findById(id).orElse(null);
    }
}
