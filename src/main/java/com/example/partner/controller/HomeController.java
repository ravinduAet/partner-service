package com.example.partner.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.partner.controller.common.RequestMappings;

import springfox.documentation.annotations.ApiIgnore;

@RestController
@ApiIgnore
public class HomeController {

    @RequestMapping(value = { RequestMappings.CONTEXT_PATH,
            RequestMappings.CONTEXT_PATH_ROOT }, produces = MediaType.TEXT_PLAIN_VALUE)
    public String home() {
        return "Partner Service is up and running!";
    }
}
