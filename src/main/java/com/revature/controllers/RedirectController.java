package com.revature.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;

@ApiIgnore
@RestController
public class RedirectController {

        @GetMapping(value = "/")
        public void redirectToServices(HttpServletResponse httpServletResponse){
            httpServletResponse.setHeader("Location", "/swagger-ui/");
            httpServletResponse.setStatus(302);
        }
}
