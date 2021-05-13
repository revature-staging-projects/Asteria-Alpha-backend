package com.revature.controllers;

import com.revature.dto.users.PrincipalDTO;
import com.revature.models.nasaImages.FavNasaImage;
import com.revature.models.nasaImages.NasaImage;
import com.revature.service.NasaImageService;
import com.revature.util.jwt.JwtParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Controller for handling request relating to images from NASA.
 */
@RestController
public class NasaImageController {

    private final NasaImageService nasa_image_service;
    private final JwtParser jwt_parser;

    @Autowired
    public NasaImageController(final JwtParser jwt_parser,final NasaImageService nasa_image_service) {
        this.nasa_image_service = nasa_image_service;
        this.jwt_parser         = jwt_parser;
    }

    /**
     * Method to retrieve a random NASA image from database.
     * @return random NASA image.
     */
    @GetMapping(path = "/image",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public NasaImage getNasaImage() {
        return nasa_image_service.getImage();

    }

    /**
     * Method to handle adding a NASA image to a user's favorites.
     * @param request Holds teh JWT which is used to identify a user.
     * @param url url of the NASA image to add ot favorites.
     */
    @PostMapping(path = "/addfavoriteimg")
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public void addImageToFavorites(final HttpServletRequest request,@RequestParam(name= "url") final String url) {
        final String token      = jwt_parser.getTokenFromHeader(request);
        final PrincipalDTO user = jwt_parser.parseToken(token);
        nasa_image_service.addImageToFavorites(user.getUsername(),url);
    }

    /**
     * Method to retrieve every NASA image which the current user has favorited.
     * @param request hold the JWT which is used ot identify the user.
     * @return
     */
    @GetMapping(path = "/getnasafavorite", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public List<FavNasaImage> getFavNasaImage(final HttpServletRequest request) {
        final String token      = jwt_parser.getTokenFromHeader(request);
        final PrincipalDTO user = jwt_parser.parseToken(token);
        return nasa_image_service.getFavImage(user.getUsername());
    }

}
