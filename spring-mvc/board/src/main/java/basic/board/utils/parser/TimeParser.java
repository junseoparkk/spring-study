package basic.board.utils.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeParser {
    public static String convertToString(final LocalDateTime time) {
        return time.format(DateTimeFormatter.ofPattern("yy-MM-dd HH:mm"));
    }
}
