package conversion;

import model.Role;
import org.eclipse.persistence.annotations.ConversionValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class RoleFormatter implements Formatter<model.Role> {

    Logger logger = LoggerFactory.getLogger(RoleFormatter.class);

    {
       System.out.println("FORMATTER HERE");
        logger.info("FORMATTER HERE");
        logger.info("FORMATTER HERE");
        logger.info("osdsd");
    }


    @Override
    public String print(Role role, Locale locale) {
        logger.info("osdsd");
        return String.valueOf(role.getId());

    }

    @Override
    public Role parse(String id, Locale locale) throws ParseException {
        Role role = new Role();
        role.setId(Integer.valueOf(id));
        return role;
    }
}
