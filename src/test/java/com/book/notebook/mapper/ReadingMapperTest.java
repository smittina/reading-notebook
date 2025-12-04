package com.book.notebook.mapper;

import com.book.notebook.model.YearDetail;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class ReadingMapperTest {

    @Test
    void convertMapToYearDetailListTest() {
        // given
        Map<Integer, Long> data = new HashMap<>();
        data.put(2022, 2L);
        data.put(2023, 3L);
        data.put(2024, 4L);
        data.put(2025, 5L);

        List<YearDetail> expected = Arrays.asList(
            new YearDetail(2022, 2L),
            new YearDetail(2023, 3L),
            new YearDetail(2024, 4L),
            new YearDetail(2025, 5L)
        );

        // when
        List<YearDetail> result = ReadingMapper.convertMapToYearDetailList(data);

        // then
        assertEquals(expected.size(), result.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).getYear(), result.get(i).getYear());
            assertEquals(expected.get(i).getNumberOfReadings(), result.get(i).getNumberOfReadings());
        }
    }


}
