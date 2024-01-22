package basic.board.utils.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeParser {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm");
    public static String convertToString(final LocalDateTime time) {
        return time.format(formatter);
    }

    public static LocalDateTime convertToTime(final String time) {
        return LocalDateTime.parse(time, formatter);
    }
}
