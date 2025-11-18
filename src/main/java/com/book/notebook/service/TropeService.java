package com.book.notebook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.notebook.entity.Trope;
import com.book.notebook.repository.TropeRepository;

@Service
public class TropeService {

    @Autowired
    private TropeRepository tropeRepository;

    public List<Trope> getTropeByBookId(Long bookId) {
        return tropeRepository.findByBookId(bookId);
    }

    public List<Trope> getTropeByTitle(String title) {
       return tropeRepository.findAllByTitle(title);
    }

}
