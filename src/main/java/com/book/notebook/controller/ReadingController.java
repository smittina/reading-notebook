package com.book.notebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.book.notebook.service.ReadingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@RestController
public class ReadingController {

    @Autowired
    private ReadingService readingService;

    @GetMapping("/readings")
    @ResponseBody
    public String getReadings() {
        
        return readingService.getAllYearsOfReadings().toString();
    }
    

}
