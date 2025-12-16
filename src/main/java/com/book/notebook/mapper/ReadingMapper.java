package com.book.notebook.mapper;

import com.book.notebook.entity.Reading;
import com.book.notebook.model.ReadingResume;
import com.book.notebook.model.YearDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Mapper relative to ReadingService
 */
public class ReadingMapper {

    /**
     * Convert map (id reading : number of reading) to list
     * @param readings map extract from repository
     * @return list of year details
     */
    public static List<YearDetail> convertMapToYearDetailList(Map<Integer, Long> readings) {
        List<YearDetail> yearDetailList = new ArrayList<>();
        readings.forEach((key, value) -> yearDetailList.add(new YearDetail(key, value)));
        return yearDetailList;
    }


}
