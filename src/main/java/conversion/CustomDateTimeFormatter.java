package conversion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class CustomDateTimeFormatter implements Formatter<LocalDateTime> {

    public CustomDateTimeFormatter() {
        super();
    }

    Logger logger = LoggerFactory.getLogger(CustomDateTimeFormatter.class);

    private final String format = "dd.MM.yyyy HH:mm";

    public final String getFormat() {
        return format;
    }

    public LocalDateTime parse(final String text, final Locale locale) throws ParseException {
        final DateTimeFormatter dateFormat = createDateFormat(locale);
        logger.info("parsed " + LocalDateTime.parse(text, dateFormat));
        return LocalDateTime.parse(text, dateFormat);
    }

    public String print(final LocalDateTime object, final Locale locale) {
        final DateTimeFormatter dateFormat = createDateFormat(locale);
        logger.info("formatted " + dateFormat.format(object));
        return dateFormat.format(object);
    }

    private DateTimeFormatter createDateFormat(final Locale locale) {
        final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(format);
        return dateFormat;
    }

}
