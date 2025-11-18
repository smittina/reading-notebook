package com.book.notebook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.book.notebook.entity.Trope;
import com.book.notebook.service.TropeService;

@RestController
public class TropeController {

     @Autowired
    private TropeService tropeService;

    @GetMapping("/trope")
    @ResponseBody
    public String getMethodName(@RequestParam String title) {
        System.out.println("Title : "+title);
        List<Trope> tropes = tropeService.getTropeByTitle(title);
        return tropes.isEmpty() ? 
            "Cette trope n'est pas répertoriée dans la base de donnée" 
            :
            "Il y a "+tropes.size()+" livres avec ce trope";
    }

}
