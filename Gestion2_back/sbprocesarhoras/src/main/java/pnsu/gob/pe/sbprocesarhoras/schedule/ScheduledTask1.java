package pnsu.gob.pe.sbprocesarhoras.schedule;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pnsu.gob.pe.sbprocesarhoras.service.taskService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class ScheduledTask1 {

   // private static final Logger log = LoggerFactory.getLogger(ScheduledTask.class);

    private final taskService service;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedDelay = 1 , timeUnit = TimeUnit.MINUTES)
    public void reportCurrentTime() {
        log.info("/////////////////////////INICIA PROCESO DE VERIFICACION////////////////////////");
        log.info("Every 1 minute - The time is now {}", dateFormat.format(new Date()));
        service.execute();
        log.info("////////////////////////FINALIZA PROCESO DE VERIFICACION////////////////////////");
    }
}
