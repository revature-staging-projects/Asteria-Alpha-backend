package com.revature.controllers;

import com.revature.dto.PrincipalDTO;
import com.revature.models.EPICImage;
import com.revature.models.FavEpicImage;
import com.revature.service.EPICService;
import com.revature.util.jwt.JwtParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
public class EPICController {
    private final EPICService epic_service;
    private final JwtParser jwt_parser;

    @Autowired
    public EPICController(final EPICService epic_service, final JwtParser jwt_parser) {
        this.epic_service = epic_service;
        this.jwt_parser   = jwt_parser;
    }


    @GetMapping(path = "/epic", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public EPICImage getEpicImage() {
        return epic_service.getEpicImageMetadata();
    }

    @GetMapping(path = "/epicfavorite", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public List<FavEpicImage> getFavEpicImage(final HttpServletRequest request) {
        final String token      = jwt_parser.getTokenFromHeader(request);
        final PrincipalDTO user = jwt_parser.parseToken(token);
        return epic_service.getFavImages(user.getUsername());
    }

    @PutMapping(path = "/addepicfavorite")
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public void addepicfavorite(final HttpServletRequest request) {
        final String token      = jwt_parser.getTokenFromHeader(request);
        final PrincipalDTO user = jwt_parser.parseToken(token);
        epic_service.addEPICImageToFavorites(user.getUsername());
    }

}
