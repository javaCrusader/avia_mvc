package InputOutput;

import dataWrappers.CountryWrapper;
import model.Country;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class IOCountry {

    public CountryWrapper load(File source, List<String> errors) throws IOException {
        errors.clear();
        List<Country> list = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(source));
            String nextLine;
            while ((nextLine = br.readLine()) != null) {
                if (nextLine.equals("Id;Country name;"))
                    continue;
                String[] words = nextLine.split(";");
                list.add(new Country().setId(Integer.parseInt(words[0])).setName(words[1]));
            }
        } catch (IOException e) {
            errors.add(e.getLocalizedMessage());
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    errors.add(e.getLocalizedMessage());
                }
            }
        }
        return new CountryWrapper(list);
    }

}
