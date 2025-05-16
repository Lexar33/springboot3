package pnsu.gob.pe.sbprocesarhoras.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.stereotype.Component;


@Component
public class ScheduledTask2 {
    //private static  final Logger log= LoggerFactory.getLogger(ScheduledTask.class);

    //@Scheduled(fixedRate=2000)
    public void prueba(){
        //log.info("hola jose Alcantara / cada 2 segundos ");
    }

    //@Scheduled(cron="*/5 * * * * * ")
    public void prueba2(){
       // log.info("hola prueba 2 / cada 5 segundos");
    }

}
