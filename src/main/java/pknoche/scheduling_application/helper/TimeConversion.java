package pknoche.scheduling_application.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeConversion {
    public static String toFormattedString(LocalDateTime localDateTime) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd    HH:mm");
        return dtf.format(localDateTime);
    }
}
