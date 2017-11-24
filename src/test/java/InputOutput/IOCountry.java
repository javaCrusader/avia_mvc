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

    public List<String> save(CountryWrapper data, File target) throws IOException {
        List<String> errors = new ArrayList<>();
        if (data.getCountryList().size() < 1) {
            errors.add("EMPTY data");
            return errors;
        }
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(target));
            bw.write("Id;Country name;\n");
            for (Country rec : data.getCountryList()) {
                bw.write(rec.getName() + "\n");
            }
        } catch (IOException e) {
            errors.add(e.getLocalizedMessage());
            return errors;
        } finally {
            if (bw != null) try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
                errors.add(e.getLocalizedMessage());
            }
        }
        return errors;
    }
}
