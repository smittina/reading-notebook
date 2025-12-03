package com.book.notebook.service;

import com.book.notebook.entity.GenreList;
import com.book.notebook.repository.GenreListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service linked to GenreList Repository
 */
@Service
public class GenreListService {

    @Autowired
    private GenreListRepository genreListRepository;

    /**
     * Get all Genre List from repository
     * @return all Genre list
     */
    public List<GenreList> getAll() {
        return genreListRepository.findAll();
    }

    /**
     * update all Genre list present in repository
     * @param genres list of genre names
     * @return new genre list saved in repository
     */
    public List<GenreList> updateGenreList(List<String> genres) {
        // Existent genres suppressed
        genreListRepository.deleteAll();
        // Add all new genres defined by the user
        List<GenreList> list = new ArrayList<>();
        genres.forEach(genre -> {
            GenreList genreList = new GenreList();
            genreList.setGenre(genre);
            list.add(genreList);
        });
        return genreListRepository.saveAll(list);
    }
}
