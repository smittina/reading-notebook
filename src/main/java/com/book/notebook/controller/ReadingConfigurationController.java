package com.book.notebook.controller;

import com.book.notebook.model.ReadingConfiguration;
import com.book.notebook.service.ReadingConfigurationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@ResponseBody
@Slf4j
public class ReadingConfigurationController {

    @Autowired
    private ReadingConfigurationService readingConfigurationService;

    @GetMapping("/config")
    public ReadingConfiguration getReadingConfiguration() {
        log.info("Get Reading Configuration");
        return readingConfigurationService.getReadingConfiguration();
    }

    @PostMapping("/config/update")
    public ReadingConfiguration updateReadingConfiguration(@RequestBody ReadingConfiguration readingConfiguration) {
        log.info("Update Reading Configuration");
        return readingConfigurationService.updateReadingConfiguration(readingConfiguration);
    }
}
