package com.revature.controllers;

import com.revature.models.NasaImage;
import com.revature.service.NasaImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NasaImageController {

    private final NasaImageService nasa_image_service;

    @Autowired
    public NasaImageController(NasaImageService nasa_image_service) {
        this.nasa_image_service = nasa_image_service;
    }

    @GetMapping(path = "/image",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public NasaImage getNasaImage() {
        return nasa_image_service.getImage();

    }

    @PostMapping(path = "/addfavoriteimg")
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public void addImageToFavorites(@RequestParam(name = "id") final int id,@RequestParam(name= "url") final String url) {
        nasa_image_service.addImageToFavorites(1,id,url);
    }

    @GetMapping(path = "/getnasafavorite", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<NasaImage> getFavNasaImage() {
        //TODO cahnge to get current user id.
        return nasa_image_service.getFavImage(1);
    }

}
