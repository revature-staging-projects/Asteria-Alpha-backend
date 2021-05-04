package com.revature.controllers;

import com.revature.dto.users.PrincipalDTO;
import com.revature.models.epicImages.EPICImage;
import com.revature.models.epicImages.FavEpicImage;
import com.revature.service.EPICService;
import com.revature.util.jwt.JwtParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * Controller for handling request concerning the EPIC images.
 */
@RestController
public class EPICController {
    private final EPICService epic_service;
    private final JwtParser jwt_parser;

    @Autowired
    public EPICController(final EPICService epic_service, final JwtParser jwt_parser) {
        this.epic_service = epic_service;
        this.jwt_parser   = jwt_parser;
    }


    /**
     * Method for handling request to retrieve current EPIC image from database.
     * @return object containing EPIC image metadata.
     */
    @GetMapping(path = "/epic", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public EPICImage getEpicImage() {
        return epic_service.getEpicImageMetadata();
    }

    /**
     * Method to handle retrieving every EPIC image which has been favorited by the currently logged in user.
     * @param request holds the JWT which is used to identify the user.
     * @return List of EPIC image metadata.
     */
    @GetMapping(path = "/epicfavorite", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public List<FavEpicImage> getFavEpicImage(final HttpServletRequest request) {
        final String token      = jwt_parser.getTokenFromHeader(request);
        final PrincipalDTO user = jwt_parser.parseToken(token);
        return epic_service.getFavImages(user.getUsername());
    }

    /**
     * Method to handle adding an EPIC image to a user's favorite lists.
     * @param request holds JWT which is used to identify a user.
     */
    @PutMapping(path = "/addepicfavorite")
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public void addepicfavorite(final HttpServletRequest request) {
        final String token      = jwt_parser.getTokenFromHeader(request);
        final PrincipalDTO user = jwt_parser.parseToken(token);
        epic_service.addEPICImageToFavorites(user.getUsername());
    }

}
