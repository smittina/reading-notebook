package com.book.notebook.service;

import com.book.notebook.entity.GenreList;
import com.book.notebook.entity.TropeList;
import com.book.notebook.mapper.ReadingConfigurationMapper;
import com.book.notebook.model.ReadingConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service linked to ReadingConfiguration
 */
@Service
public class ReadingConfigurationService {

    @Autowired
    private GenreListService genreListService;
    @Autowired
    private TropeListService tropeListService;

    /**
     * Get Reading Configuration from GenreList and TropeList Repository
     * @return reading configuration
     */
    public ReadingConfiguration getReadingConfiguration() {
        return new ReadingConfiguration(
                ReadingConfigurationMapper.convertGenreListToNameList(genreListService.getAll()),
                ReadingConfigurationMapper.convertTropeListToNameList(tropeListService.getAll())
        );
    }

    /**
     * Update GenreList and TropeList from ReadingConfiguration
     * @param readingConfiguration reading configuration
     * @return new ReadingConfiguration with Genre and Trope saved in repository
     */
    public ReadingConfiguration updateReadingConfiguration(ReadingConfiguration readingConfiguration) {
        List<GenreList> newGenreList = genreListService.updateGenreList(readingConfiguration.getGenres());
        List<TropeList> newTropeList = tropeListService.updateTropeList(readingConfiguration.getTropes());
        return new ReadingConfiguration(
                ReadingConfigurationMapper.convertGenreListToNameList(newGenreList),
                ReadingConfigurationMapper.convertTropeListToNameList(newTropeList)
        );
    }
}
