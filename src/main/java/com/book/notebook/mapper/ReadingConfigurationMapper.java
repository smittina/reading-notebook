package com.book.notebook.mapper;

import com.book.notebook.entity.GenreList;
import com.book.notebook.entity.TropeList;

import java.util.List;

public class ReadingConfigurationMapper {

    public static List<String> convertGenreListToNameList(List<GenreList> genreLists) {
        return genreLists.stream().map(GenreList::getGenre).toList();
    }

    public static List<String> convertTropeListToNameList(List<TropeList> tropeLists) {
        return tropeLists.stream().map(TropeList::getTrope).toList();
    }
}
