package dataWrappers;

import model.Country;

import java.util.ArrayList;
import java.util.List;

public class CountryWrapper {

    List<Country> countryList = new ArrayList<>();

    public CountryWrapper(List<Country> countries) {
        countryList = countries;
    }

    public List<Country> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<Country> countryList) {
        this.countryList = countryList;
    }

}
