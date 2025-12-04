package com.book.notebook.mapper;

import com.book.notebook.entity.GenreList;
import com.book.notebook.entity.TropeList;

import java.util.List;

/**
 * Mapper relative to ReadingConfigurationService
 */
public class ReadingConfigurationMapper {

    /**
     * Convert list of GenreList to list of genre name
     * @param genreLists list of genreLists from repository
     * @return list of genre name
     */
    public static List<String> convertGenreListToNameList(List<GenreList> genreLists) {
        return genreLists.stream().map(GenreList::getGenre).toList();
    }

    /**
     * Convert list of TropeList to list of trope name
     * @param tropeLists list of tropeLists from repository
     * @return list of trope name
     */
    public static List<String> convertTropeListToNameList(List<TropeList> tropeLists) {
        return tropeLists.stream().map(TropeList::getTrope).toList();
    }

}
