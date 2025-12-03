package com.book.notebook.mapper;

import com.book.notebook.model.YearDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReadingMapper {

    public static List<YearDetail> convertMapToYearDetailList(Map<Integer, Long> readings) {
        List<YearDetail> yearDetailList = new ArrayList<>();
        readings.forEach((key, value) -> {
            yearDetailList.add(new YearDetail(key, value));
        });
        return yearDetailList;
    }

}
