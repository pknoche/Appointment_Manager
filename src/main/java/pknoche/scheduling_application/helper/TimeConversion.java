package pknoche.scheduling_application.helper;

import java.time.*;
import java.time.format.DateTimeFormatter;

public abstract class TimeConversion {
    private static final LocalTime openTime = LocalTime.of(8,0);
    private static final LocalTime closeTime = LocalTime.of(22,0);
    private static final ZoneId timeZone = ZoneId.of("America/New_York"); // time zone of business headquarters

    public static String toFormattedString(LocalDateTime localDateTime) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd    HH:mm");
        return dtf.format(localDateTime);
    }

    public static LocalTime LocalOpenTime() {
        ZonedDateTime hqOpenTime = ZonedDateTime.of(LocalDate.now(), openTime, timeZone);
        ZonedDateTime userOpenTime = hqOpenTime.withZoneSameInstant(ZoneId.systemDefault()); // convert headquarters open time to user's local time zone
        return userOpenTime.toLocalTime();
    }

    public static LocalTime LocalCloseTime() {
        ZonedDateTime hqCloseTime = ZonedDateTime.of(LocalDate.now(), closeTime, timeZone);
        ZonedDateTime userCloseTime = hqCloseTime.withZoneSameInstant(ZoneId.systemDefault()); // convert headquarters open time to user's local time zone
        return userCloseTime.toLocalTime();
    }
}
