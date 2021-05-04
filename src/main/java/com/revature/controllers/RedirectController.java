package com.revature.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;

/**
 * Controller which is used to redirect links on the website.
 */
@ApiIgnore
@RestController
public class RedirectController {

    /**
     * redirect the root page to the swagger-ui.
     * @param httpServletResponse response which contains the redirect instructions for the browser.
     */
        @GetMapping(value = "/")
        public void redirectToServices(HttpServletResponse httpServletResponse){
            httpServletResponse.setHeader("Location", "/swagger-ui/");
            httpServletResponse.setStatus(302);
        }
}
