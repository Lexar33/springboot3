package pe.gob.pnsu.controlasistenciaws.schedule;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pe.gob.pnsu.controlasistenciaws.service.IAsistenciaParcial;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class ScheduledTask1 {

    // private static final Logger log = LoggerFactory.getLogger(ScheduledTask.class);

    private final IAsistenciaParcial mainService;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedDelay = 3 , timeUnit = TimeUnit.MINUTES)
    public void reportCurrentTime() {
        log.info("/////////////////////////INICIA PROCESAMIENTO////////////////////////");

        log.info("Every 3 minutes - The time is now {}", dateFormat.format(new Date()));

        //mainService.registrarcontrolasistenciaparcial("01/01/2024","23/08/2024");
        mainService.registrarcontrolasistenciaparcial("19/08/2024","23/08/2024");


        log.info("////////////////////////FINALIZA PROCESAMIENTO////////////////////////");

    }


}
