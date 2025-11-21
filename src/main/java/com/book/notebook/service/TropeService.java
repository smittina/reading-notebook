package com.book.notebook.service;

import java.util.ArrayList;
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

    /**
     * Save a list of new Trope Entities in database
     * @param tropeTitle trope name
     * @param idBook book Id
     * @return list of new tropes saved
     */
    public List<Trope> createNewTropes(List<String> tropeTitle, Long idBook) {
        List<Trope> newTropes = new ArrayList<>();
        for (String tropeName : tropeTitle) {
            Trope trope = new Trope();
            trope.setTitle(tropeName);
            trope.setBookId(idBook);
            newTropes.add(trope);
        }
        return tropeRepository.saveAll(newTropes);
    }

}
