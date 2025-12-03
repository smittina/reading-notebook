package com.book.notebook.service;

import com.book.notebook.entity.GenreList;
import com.book.notebook.entity.TropeList;
import com.book.notebook.repository.TropeListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service linked to TropeList Repository
 */
@Service
public class TropeListService {

    @Autowired
    private TropeListRepository tropeListRepository;

    /**
     * Get all Trope List from repository
     * @return all Trope List
     */
    public List<TropeList> getAll() {
        return tropeListRepository.findAll();
    }

    /**
     * update all Trope List existent in repository
     * @param tropes list of all trope names
     * @return list of new Trope Lists present in repository
     */
    public List<TropeList> updateTropeList(List<String> tropes) {
        // Existent genres suppressed
        tropeListRepository.deleteAll();
        // Add all new genres defined by the user
        List<TropeList> list = new ArrayList<>();
        tropes.forEach(trope -> {
            TropeList tropeList = new TropeList();
            tropeList.setTrope(trope);
            list.add(tropeList);
        });
        return tropeListRepository.saveAll(list);
    }
}
