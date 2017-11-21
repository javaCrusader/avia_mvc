package conversion;

import model.Hobby;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class HobbyFormatter implements Formatter<Hobby> {


    {
    }

    @Override
    public String print(Hobby hobby, Locale locale) {
        return hobby.getId();
    }

    @Override
    public Hobby parse(String id, Locale locale) throws ParseException {
        Hobby hobby = new Hobby();
        hobby.setId(id);
        return hobby;
    }
}
