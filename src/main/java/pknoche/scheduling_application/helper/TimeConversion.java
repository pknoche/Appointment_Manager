package pknoche.scheduling_application.helper;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public class TimeConversion {
    private static final LocalTime openTime = LocalTime.of(8,0);
    private static final LocalTime closeTime = LocalTime.of(22,0);
    private static final ZoneId timeZoneHQ = ZoneId.of("America/New_York"); // time zone of business headquarters

    public static String toFormattedString(LocalDateTime localDateTime) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd    HH:mm");
        return dtf.format(localDateTime);
    }
    public static String toUTC(LocalDateTime localDateTime) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ZonedDateTime UTCTime = ZonedDateTime.of(localDateTime,ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC"));
        return dtf.format(UTCTime) + " UTC";
    }

    public static LocalTime LocalOpenTime() {
        ZonedDateTime hqOpenTime = ZonedDateTime.of(LocalDate.now(), openTime, timeZoneHQ);
        ZonedDateTime userOpenTime = hqOpenTime.withZoneSameInstant(ZoneId.systemDefault()); // convert headquarters open time to user's local time zone
        return userOpenTime.toLocalTime();
    }

    public static LocalTime LocalCloseTime() {
        ZonedDateTime hqCloseTime = ZonedDateTime.of(LocalDate.now(), closeTime, timeZoneHQ);
        ZonedDateTime userCloseTime = hqCloseTime.withZoneSameInstant(ZoneId.systemDefault()); // convert headquarters open time to user's local time zone
        return userCloseTime.toLocalTime();
    }

    public static LocalDate firstDayNextWeek() {
        return LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
    }

    public static LocalDate firstDayThisWeek() {
        return LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
    }

    public static LocalDate firstDayNextMonth() {
        return LocalDate.now().with(TemporalAdjusters.firstDayOfNextMonth());
    }

    public static LocalDate firstDayThisMonth() {
        return LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
    }
}
