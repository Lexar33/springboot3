package com.pnsu.spdriveapi.controller;

import com.pnsu.spdriveapi.service.GmailAPIService;
import com.pnsu.spdriveapi.service.gmaildriveService;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.google.api.services.gmail.model.Message;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class spController {

    //private final driveService driveService;
    private final gmaildriveService gmailDriveService;
    private final GmailAPIService gmailAPIService;

    @GetMapping("/")
    String home() {
        return "holaaaa";
    }

    /*
        @PostMapping("/upload")
        String subida(@RequestPart(value = "file") MultipartFile file) throws GeneralSecurityException, IOException {
            return driveService.subida(file);
        }
    */
    @PostMapping("/uploadnotify")
    String uploadSendMail(@RequestPart(value = "file") MultipartFile file) throws GeneralSecurityException, MessagingException, IOException {
        /*
        String id= gmailDriveService.upload(file);
        if(id!=null) {
            log.info("id:"+id);
        }
        */
        /*
        Message message = gmailDriveService.sendEmail("jalcantara@vivienda.gob.pe", List.of("jalcantara@vivienda.gob.pe","jalcantararivera@gmail.com"));
        if (message != null) {
            log.info("Mensaje enviado");
            //return id;
        }
        return null;

         */

        gmailAPIService.sendMessage("prueba", "Holaaa mundo", file);
        log.info("*************************************************");
        return "enviado";
    }
/*
    @GetMapping("/gmail")
    void gmail() throws GeneralSecurityException, MessagingException, IOException {
        //gmailService.gmail();
        Message message = gmailDriveService.sendEmail("jalcantara@vivienda.gob.pe", List.of("jalcantara@vivienda.gob.pe","jalcantararivera@gmail.com"));
        if (message != null) {
            log.info("Mensaje enviado");
        }
    }
*/


}
