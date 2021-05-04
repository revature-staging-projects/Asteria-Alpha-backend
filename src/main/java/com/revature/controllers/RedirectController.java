package com.revature.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class RedirectController {

//        @GetMapping(value = "/")
//        public void redirectToServices(HttpServletResponse httpServletResponse){
//            httpServletResponse.setHeader("Location", "/swagger-ui/");
//            httpServletResponse.setStatus(302);
//        }
}
