package pe.gob.pnsu.controlasistenciaws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ControlAsistenciaWsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ControlAsistenciaWsApplication.class, args);
    }

}
