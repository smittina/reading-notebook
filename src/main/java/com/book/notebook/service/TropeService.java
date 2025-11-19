package com.book.notebook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.notebook.entity.Trope;
import com.book.notebook.repository.TropeRepository;

/**
 * Service linked to TropeRepository
 */
@Service
public class TropeService {

    @Autowired
    private TropeRepository tropeRepository;

    /**
     * Get all tropes for a specific book id
     * @param bookId book id
     * @return all tropes assigned to a specific book
     */
    public List<Trope> getTropeByBookId(Long bookId) {
        return tropeRepository.findByBookId(bookId);
    }

    /**
     * Get all unique trope titles in database
     * @return list of all unique trope titles
     */
    public List<String> getAllUniqueTropes(){
        return tropeRepository.findAll()
                .stream()
                .map(Trope::getTitle)
                .distinct()
                .toList();
    }

}
