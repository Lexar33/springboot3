package pe.gob.pnsu.controlasistenciaws.util;

import org.springframework.stereotype.Component;

import java.time.*;
import java.util.Date;

@Component
public class EAIUtil {

    public LocalDate convertToLocalDateViaMilisecond(Date dateToConvert) {

        if (dateToConvert == null) {
            return null;
        } else {
            return Instant.ofEpochMilli(dateToConvert.getTime())
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
        }
    }

    public LocalDateTime convertToLocalDateTimeViaMilisecond(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public LocalDate convertToLocalDateFromSqlDate(java.sql.Date datetoConvert) {
        if (datetoConvert == null)
            return null;
        else
            return datetoConvert.toLocalDate();
    }

    public LocalTime convertToLocalTimeFromSqlTime(java.sql.Time timetoConvert) {
        if (timetoConvert == null)
            return null;
        else
            return timetoConvert.toLocalTime();
    }

}
