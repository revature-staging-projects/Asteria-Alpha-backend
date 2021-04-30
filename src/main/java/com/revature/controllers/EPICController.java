package com.revature.controllers;

import com.revature.models.EPICImage;
import com.revature.models.FavEpicImage;
import com.revature.service.EPICService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


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

    @GetMapping(path = "/epicfavorite", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FavEpicImage> getFavEpicImage() {
        //TODO change to get current user id
        return epic_service.getFavImages(1);
    }

    @PutMapping(path = "/addepicfavorite", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addepicfavorite(@RequestBody final FavEpicImage image) {
        epic_service.addEPICImageToFavorites(image);
    }

}
