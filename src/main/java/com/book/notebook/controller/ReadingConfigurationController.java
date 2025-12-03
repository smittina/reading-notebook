package com.book.notebook.controller;

import com.book.notebook.model.ReadingConfiguration;
import com.book.notebook.service.ReadingConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
@RestController
@ResponseBody
public class ReadingConfigurationController {

    @Autowired
    private ReadingConfigurationService readingConfigurationService;

    @GetMapping("/config")
    public ReadingConfiguration getReadingConfiguration() {
        return readingConfigurationService.getReadingConfiguration();
    }

    @PostMapping("/config/update")
    public ReadingConfiguration updateReadingConfiguration(@RequestBody ReadingConfiguration readingConfiguration) {
        return readingConfigurationService.updateReadingConfiguration(readingConfiguration);
    }
}
