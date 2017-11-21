package conversion;

import model.Role;
import org.eclipse.persistence.annotations.ConversionValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class RoleConverter implements Converter<Role,String> {

    Logger logger = LoggerFactory.getLogger(RoleFormatter.class);

    {
        System.out.println("converter HERE");
        logger.info("converter HERE");
        logger.info("converter HERE");
        logger.info("osdsd");
    }


    @Override
    public String convert(Role role) {
        return String.valueOf(role.getId());
    }
}
