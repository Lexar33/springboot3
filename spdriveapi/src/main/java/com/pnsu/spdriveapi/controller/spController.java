package com.pnsu.spdriveapi.controller;

import com.pnsu.spdriveapi.service.driveService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    String subida(@RequestPart(value = "file") MultipartFile file) throws GeneralSecurityException, IOException{

    //String subida() throws GeneralSecurityException, IOException {
        return service.subida(file);
    }

}
