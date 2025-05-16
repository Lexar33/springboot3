package pe.gob.pnsu.websocket.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class mainController {

    @GetMapping
    public String hellowWorld() throws URISyntaxException, InterruptedException {

        String URL= "ws://localhost:8877";
        JavaWebSocketClient client= new JavaWebSocketClient(new URI(URL));
        client.connectBlocking();

        client.send("{\"command\":\"getInfo\"}");

        TimeUnit.SECONDS.sleep(1);
        String message= client.message;
        log.info(message);
        //client.closeBlocking();
        client.closeBlocking();

        //log.info();

       // client.setAttachment();
        return "API WEBSOCKET" +'\n'+ message;


    }


}
