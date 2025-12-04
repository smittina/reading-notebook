package com.book.notebook.mapper;

import com.book.notebook.entity.GenreList;
import com.book.notebook.entity.TropeList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class ReadingConfigurationMapperTest {

    @Test
    void convertGenreListToNameListTest() {
        // given
        List<GenreList> data = Arrays.asList(
                new GenreList(1L, "Genre1"),
                new GenreList(2L, "Genre2"),
                new GenreList(3L, "Genre3")
        );

        List<String> expected = Arrays.asList("Genre1", "Genre2", "Genre3");

        // when
        List<String> result = ReadingConfigurationMapper.convertGenreListToNameList(data);

        // then
        assertEquals(expected.size(), result.size());
        for(int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), result.get(i));
        }
    }

    @Test
    void convertTropeListToNameListTest() {
        // given
        List<TropeList> data = Arrays.asList(
                new TropeList(1L, "Trope1"),
                new TropeList(2L, "Trope2"),
                new TropeList(3L, "Trope3")
        );

        List<String> expected = Arrays.asList("Trope1", "Trope2", "Trope3");

        // when
        List<String> result = ReadingConfigurationMapper.convertTropeListToNameList(data);

        // then
        assertEquals(expected.size(), result.size());
        for(int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), result.get(i));
        }

    }
}
