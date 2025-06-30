package com.pnsu.spdriveapi.service;

import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import com.pnsu.spdriveapi.model.GmailCredential;
import com.pnsu.spdriveapi.model.GoogleTokenResponse;
import jakarta.activation.DataHandler;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.Session;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.util.ByteArrayDataSource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import jakarta.activation.DataSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;

@Service
@Slf4j
public class GmailAPIService {

    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private HttpTransport httpTransport;
    private GmailCredential gmailCredential;

    //@Value("${spring.google.client-id}")
    private String clientId = "778358890374-ag54jnjqsq8h0lrd0ejk09f1ic3s0t02.apps.googleusercontent.com";
    //@Value("${spring.google.client-secret}")
    private String secretKey = "dBj0dttK_9PlxvgEP1U2GTRn";
    //@Value("${access-token}")
    private String refreshToken = "1//04BFR7SnGOSU9CgYIARAAGAQSNwF-L9IryTH9a8_VCgfRCorknWvDriecTZ-yUC7wbGef7_diB6HAqbz8EqxwL2XhkylemWpUAMM";
    //@Value("${spring.google.from-email}")
    private String fromEmail = "a_pnsu@vivienda.gob.pe";
    //@Value("${spring.google.to-email}")
    private String toEmail = "jalcantararivera@gmail.com";

    @SneakyThrows
    public GmailAPIService() {

        this.httpTransport = GoogleNetHttpTransport.newTrustedTransport();

        this.gmailCredential = new GmailCredential(
                clientId,
                secretKey,
                refreshToken,
                null,
                null,
                fromEmail
        );
    }

    public boolean sendMessage(String subject, String body, MultipartFile attachment) throws MessagingException, IOException {
        refreshAccessToken();

        Message message = createMessageWithEmail(createEmail(toEmail, gmailCredential.userEmail(), subject, body, attachment));
        ///////////////////////////////////////////////
        com.google.api.services.drive.model.File fileMetadata = new com.google.api.services.drive.model.File();
        fileMetadata.setName(attachment.getResource().getFilename());

        File uploadFile = createDrive().files().create(fileMetadata, new InputStreamContent(
                        attachment.getContentType(),
                        new ByteArrayInputStream(attachment.getBytes())))
                .setFields("id")
                .execute();
        log.info("file id" + uploadFile.getId());
        ///////////////////////////////////////////////
        return createGmail()
                .users()
                .messages()
                .send(gmailCredential.userEmail(), message)
                .execute()
                .getLabelIds()
                .contains("SENT");

    }

    private Drive createDrive() {
        Credential credentialDrive = authorize();
        return new Drive.Builder(httpTransport, JSON_FACTORY, credentialDrive).setApplicationName("SpDriveApi").build();
    }

    private Gmail createGmail() {
        Credential credential = authorize();
        return new Gmail.Builder(httpTransport, JSON_FACTORY, credential).setApplicationName("SpDriveApi").build();

    }

    private MimeMessage createEmail(
            String to,
            String from,
            String subject,
            String bodyText,
            MultipartFile attachment) throws MessagingException {

        MimeMessage email = new MimeMessage(Session.getDefaultInstance(new Properties(), null));

        email.setFrom(new InternetAddress(from));

        email.addRecipient(jakarta.mail.Message.RecipientType.TO, new InternetAddress(to));

        email.setSubject(subject);

        email.setText(bodyText);

        email = addAttachmentToEmail(email, bodyText, attachment);

        return email;

    }

    private MimeMessage addAttachmentToEmail(MimeMessage email, String bodyText,
                                             MultipartFile attachment) {

        if (attachment.isEmpty()) {
            return email;
        }

        try {
            Multipart multipart = new MimeMultipart();
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(bodyText, "text/plain");
            multipart.addBodyPart(mimeBodyPart);
            mimeBodyPart = new MimeBodyPart();
            DataSource ds = new ByteArrayDataSource(attachment.getBytes(), attachment.getContentType());
            mimeBodyPart.setDataHandler(new DataHandler(ds));
            mimeBodyPart.setFileName(attachment.getOriginalFilename());
            multipart.addBodyPart(mimeBodyPart);
            email.setContent(multipart);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Not able to process request");
        }
        return email;
    }

    private Message createMessageWithEmail(MimeMessage emailContent)
            throws MessagingException, IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        emailContent.writeTo(buffer);
        return new Message().setRaw(Base64.encodeBase64URLSafeString(buffer.toByteArray()));
    }

    private Credential authorize() {

        try {

            TokenResponse tokenResponse = refreshAccessToken();
            return new Credential(BearerToken.authorizationHeaderAccessMethod()).setFromTokenResponse(
                    tokenResponse);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Not able to process request.");
        }

    }

    private TokenResponse refreshAccessToken() {

        RestTemplate restTemplate = new RestTemplate();

        GmailCredential gmailCredentialsDto = new GmailCredential(
                clientId,
                secretKey,
                refreshToken,
                "refresh_token",
                null,
                null
        );

        HttpEntity<GmailCredential> entity = new HttpEntity<>(gmailCredentialsDto);

        try {
            GoogleTokenResponse response = restTemplate.postForObject(
                    "https://www.googleapis.com/oauth2/v4/token",
                    entity,
                    GoogleTokenResponse.class);

            gmailCredential = new GmailCredential(
                    clientId,
                    secretKey,
                    refreshToken,
                    null,
                    response.getAccessToken(),
                    fromEmail
            );

            return response;

        } catch (Exception e) {

            e.printStackTrace();

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Not able to process request.");

        }
    }
}
