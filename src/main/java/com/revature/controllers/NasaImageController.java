package com.revature.controllers;

import com.revature.dto.PrincipalDTO;
import com.revature.models.NasaImage;
import com.revature.service.NasaImageService;
import com.revature.util.jwt.JwtParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class NasaImageController {

    private final NasaImageService nasa_image_service;
    private final JwtParser jwt_parser;

    @Autowired
    public NasaImageController(final JwtParser jwt_parser,final NasaImageService nasa_image_service) {
        this.nasa_image_service = nasa_image_service;
        this.jwt_parser         = jwt_parser;
    }

    @GetMapping(path = "/image",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public NasaImage getNasaImage() {
        return nasa_image_service.getImage();

    }

    @PostMapping(path = "/addfavoriteimg")
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public void addImageToFavorites(final HttpServletRequest request,@RequestParam(name= "url") final String url) {
        final String token      = jwt_parser.getTokenFromHeader(request);
        final PrincipalDTO user = jwt_parser.parseToken(token);
        nasa_image_service.addImageToFavorites(user.getUsername(),url);
    }

    @GetMapping(path = "/getnasafavorite", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public List<NasaImage> getFavNasaImage(final HttpServletRequest request) {
        final String token      = jwt_parser.getTokenFromHeader(request);
        final PrincipalDTO user = jwt_parser.parseToken(token);
        System.out.println("\n\n--------\nusername is: " + user.getUsername());
        return nasa_image_service.getFavImage(user.getUsername());
    }



}
