package InputOutput;

import dataWrappers.CityWrapper;
import dataWrappers.CountryWrapper;
import model.City;
import model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IOCity {

    @Autowired
    IOCountry ioCountry;

    public CityWrapper load(File sourceCountry, File sourceCity, List<String> errors) throws IOException {
        errors.clear();
        List<City> list = new ArrayList<>();
        BufferedReader br = null;
        CountryWrapper countryWrapper = ioCountry.load(sourceCountry, errors);
        try {
            br = new BufferedReader(new FileReader(sourceCity));
            String nextLine;
            while ((nextLine = br.readLine()) != null) {
                if (nextLine.equals("Id;City name;Country name"))
                    continue;
                String[] words = nextLine.split(";");
                if (words.length > 3) {
                    errors.add("incorrect csv format");
                    return null;
                }
                List<Country> countries = countryWrapper.getCountryList().stream().filter(country -> country.getName().equals(words[2])).collect(Collectors.toList());
                if (countries.size() == 1) {
                    City city = new City();
                    city.setId(Integer.parseInt(words[0]));
                    city.setCountry(countries.get(0));
                    city.setName(words[1]);
                    list.add(city);
                } else {
                    errors.add("incorrect csv format (problem with countries)");
                    return null;
                }
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
        return new CityWrapper(list);
    }

}
