package com.book.notebook.controller;

import com.book.notebook.entity.Reading;
import com.book.notebook.model.FormInformation;
import com.book.notebook.model.ReadingDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.book.notebook.service.ReadingService;

import java.util.List;
import java.util.Map;


@RestController
@ResponseBody
public class ReadingController {

    @Autowired
    private ReadingService readingService;

    @GetMapping("/readings")
    public Map<Integer, Long> getReadings() {
        return readingService.getAllYearsOfReadings();
    }

    @GetMapping("/readings/{year}")
    public List<Reading> getReadingsByYear(@PathVariable("year") int year) {
        return readingService.getAllByYearOfReading(year);
    }

    @GetMapping("/readings/{year}/{month}")
    public List<Reading> getReadingsByYearAndMonth(@PathVariable("year") int year, @PathVariable("month") int month) {
        return readingService.getAllByMonthAndYearOfReading(year, month);
    }

    @GetMapping("/readings/reading-detail/{readingId}")
    public ReadingDetail getReadingDetail(@PathVariable Long readingId) {
        return readingService.getReadingDetail(readingId);
    }

    @GetMapping("/readings/form-information")
    public FormInformation getFormInformation() {
        return readingService.getFormInformation();
    }

    @PostMapping("/readings/create")
    public Reading createReading(@RequestBody ReadingDetail readingDetail) {
        return readingService.createNewReading(readingDetail);
    }

}
