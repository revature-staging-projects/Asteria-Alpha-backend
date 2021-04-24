package com.revature.controllers;

import com.revature.dto.ImageCollectionDTO;
import com.revature.dto.NasaImageDTO;
import com.revature.models.NasaImage;
import com.revature.service.NasaImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/image")
public class NasaImageController {

    private final NasaImageService nasa_image_service;

    @Autowired
    public NasaImageController(NasaImageService nasa_image_service) {
        this.nasa_image_service = nasa_image_service;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public NasaImage getNasaImage() {
        return nasa_image_service.getImage();

    }

}
