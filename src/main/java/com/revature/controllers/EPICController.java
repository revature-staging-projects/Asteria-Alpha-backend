package com.revature.controllers;

import com.revature.models.EPICImage;
import com.revature.service.EPICService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
public class EPICController {
    private final EPICService epic_service;

    @Autowired
    public EPICController(final EPICService epic_service) {
        this.epic_service = epic_service;
    }


    @GetMapping(path = "/epic", produces = MediaType.APPLICATION_JSON_VALUE)
    public EPICImage getEpicImage() {
        return epic_service.getEpicImageMetadata();
    }


}
