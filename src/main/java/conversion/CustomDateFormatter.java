
package conversion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class CustomDateFormatter implements Formatter<LocalDate> {

    Logger logger = LoggerFactory.getLogger(CustomDateFormatter.class);

    public CustomDateFormatter() {
        super();
    }

    private final String format = "dd.MM.yyyy";

    public final String getFormat() {
        return format;
    }

    public LocalDate parse(final String text, final Locale locale) throws ParseException {
        final DateTimeFormatter dateFormat = createDateFormat(locale);
        logger.info("parsed " + LocalDate.parse(text, dateFormat));
        return LocalDate.parse(text, dateFormat);
    }

    public String print(final LocalDate object, final Locale locale) {
        final DateTimeFormatter dateFormat = createDateFormat(locale);
        logger.info("formatted " + object.format(dateFormat));
        return object.format(dateFormat);
    }

    private DateTimeFormatter createDateFormat(final Locale locale) {
        final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(format);
        return dateFormat;
    }

}
