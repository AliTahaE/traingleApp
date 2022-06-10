package com.example.traingleApp.controllers;

import com.example.traingleApp.services.TraingleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TraingleController {

    private final TraingleService traingleService;

    TraingleController(TraingleService traingleService) {
        this.traingleService =  traingleService;
    }

    @GetMapping("/traingle")
    public String getType(@RequestParam String a, @RequestParam String b, @RequestParam String c) {
        try {
            return traingleService.getType(Integer.valueOf(a),
                    Integer.valueOf(b), Integer.valueOf(c)).toString();
        } catch (NumberFormatException e) {
            return "Please Use Only Numbers";
        }
    }



}
