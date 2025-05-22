package com.pnsu.spdriveapi.controller;

import com.pnsu.spdriveapi.service.driveService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
@AllArgsConstructor
public class spController {

    private final driveService service;
    @GetMapping("/")
    String home() {
        return "holaaaa";
    }

    @PostMapping("/upload")
    //String subida(RequestBody MultipartFile file) {

    String subida() throws GeneralSecurityException, IOException {
        return service.subida();
    }

}
