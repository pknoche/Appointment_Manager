package pknoche.scheduling_application.helper;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * Contains methods for various time-related calculations.
 */
public class TimeConversion {
    /**
     * Open time of the business at headquarters location.
     */
    private static final LocalTime openTime = LocalTime.of(8,0);
    /**
     * Close time of the business at headquarters location.
     */
    private static final LocalTime closeTime = LocalTime.of(22,0);
    /**
     * Time zone of the business at headquarters location.
     */
    private static final ZoneId timeZoneHQ = ZoneId.of("America/New_York"); // time zone of business headquarters

    /**
     * Converts a localDateTime to a formatted string in "yyyy-MM-dd    HH:mm" format.
     *
     * @param localDateTime time to be formatted
     * @return String containing formatted time
     */
    public static String toFormattedString(LocalDateTime localDateTime) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd    HH:mm");
        return dtf.format(localDateTime);
    }

    /**
     * Converts a localTime to a formatted string in "HH:mm" format.
     *
     * @param localTime time to be formatted
     * @return String containing formatted time
     */
    public static String toFormattedString(LocalTime localTime) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        return dtf.format(localTime);
    }

    /**
     * Converts a localDateTime to a formatted string in UTC.
     *
     * @param localDateTime time to be converted and formatted
     * @return String containing formatted time in UTC
     */
    public static String toUTC(LocalDateTime localDateTime) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ZonedDateTime UTCTime = ZonedDateTime.of(localDateTime,ZoneId.systemDefault()).
                withZoneSameInstant(ZoneId.of("UTC"));
        return dtf.format(UTCTime) + " UTC";
    }

    /**
     * Calculates open time of business in system default time zone.
     *
     * @return open time in system default time zone
     */
    public static LocalTime LocalOpenTime() {
        ZonedDateTime hqOpenTime = ZonedDateTime.of(LocalDate.now(), openTime, timeZoneHQ);
        ZonedDateTime userOpenTime = hqOpenTime.withZoneSameInstant(ZoneId.systemDefault()); // convert headquarters open time to user's local time zone
        return userOpenTime.toLocalTime();
    }

    /**
     * Calculates close time of business in system default time zone.
     *
     * @return close time in system default time zone
     */
    public static LocalTime LocalCloseTime() {
        ZonedDateTime hqCloseTime = ZonedDateTime.of(LocalDate.now(), closeTime, timeZoneHQ);
        ZonedDateTime userCloseTime = hqCloseTime.withZoneSameInstant(ZoneId.systemDefault()); // convert headquarters open time to user's local time zone
        return userCloseTime.toLocalTime();
    }

    /**
     * Calculates the date of the first day of the next week.
     *
     * @return date of next Sunday
     */
    public static LocalDate firstDayNextWeek() {
        return LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
    }

    /**
     * Calculates the date of the first day of the current week.
     *
     * @return date of Sunday in the current week
     */
    public static LocalDate firstDayThisWeek() {
        return LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
    }

    /**
     * Calculates the date of the first day of the next month.
     *
     * @return date of the first day of the next month
     */
    public static LocalDate firstDayNextMonth() {
        return LocalDate.now().with(TemporalAdjusters.firstDayOfNextMonth());
    }

    /**
     * Calculates the date of the first day of the current month.
     *
     * @return date of the first day of the current month
     */
    public static LocalDate firstDayThisMonth() {
        return LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
    }
}
