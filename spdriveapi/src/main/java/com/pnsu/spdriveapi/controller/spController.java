package com.pnsu.spdriveapi.controller;

import com.pnsu.spdriveapi.service.driveService;
import com.pnsu.spdriveapi.service.gmailService;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
@AllArgsConstructor
public class spController {

    private final driveService driveService;
    private final gmailService gmailService;

    @GetMapping("/")
    String home() {
        return "holaaaa";
    }

    @PostMapping("/upload")
    String subida(@RequestPart(value = "file") MultipartFile file) throws GeneralSecurityException, IOException{

    //String subida() throws GeneralSecurityException, IOException {
        return driveService.subida(file);
    }

    @GetMapping("/gmail")
    void gmail() throws GeneralSecurityException, MessagingException,IOException {
        //gmailService.gmail();
        gmailService.sendEmail("jalcantara@vivienda.gob.pe","jalcantararivera@gmail.com");
    }

}
