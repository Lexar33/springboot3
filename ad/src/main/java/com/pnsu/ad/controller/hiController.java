package com.pnsu.ad.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class hiController {

    @GetMapping("/")
    public String hi() {
        return "hi JAARR";
    }


}
