package com.book.notebook.controller;

import com.book.notebook.entity.Reading;
import com.book.notebook.mapper.ReadingMapper;
import com.book.notebook.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.book.notebook.service.ReadingService;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@ResponseBody
@Slf4j
public class ReadingController {

    @Autowired
    private ReadingService readingService;

    @GetMapping("/readings")
    public List<YearDetail> getReadings() {
        log.info("Get Readings");
        return ReadingMapper.convertMapToYearDetailList(readingService.getAllYearsOfReadings());
    }

    @GetMapping("/readings/{year}")
    public List<BookResume> getReadingsByYear(@PathVariable("year") int year) {
        log.info("Get Readings by Year with parameters :"+year);
        return readingService.getAllBookResumesByYear(year);
    }

    @GetMapping("/readings/{year}/{month}")
    public List<Reading> getReadingsByYearAndMonth(@PathVariable("year") int year, @PathVariable("month") int month) {
        return readingService.getAllByMonthAndYearOfReading(year, month);
    }

    @GetMapping("/all-readings")
    public List<ReadingResume> getAllReadings() {
        log.info("Get All Reading Resumes");
        return readingService.getAllReadingResumes();
    }

    @GetMapping("/readings/reading-detail/{readingId}")
    public ReadingDetail getReadingDetail(@PathVariable Long readingId) {
        log.info("Get ReadingDetails with parameter Reading Id :"+readingId);
        return readingService.getReadingDetail(readingId);
    }

    @GetMapping("/readings/form-information")
    public FormInformation getFormInformation() {
        log.info("Get Form Information");
        return readingService.getFormInformation();
    }

    @PostMapping("/readings/create")
    public Reading createReading(@RequestBody ReadingDetail readingDetail) {
        log.info("Create Reading with body : {}", readingDetail);
        return readingService.createNewReading(readingDetail);
    }

}
