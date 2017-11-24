package dataWrappers;

import model.City;

import java.util.ArrayList;
import java.util.List;

public class CityWrapper {

    List<City> cityList = new ArrayList<>();

    public CityWrapper(List<City> cities) {
        cityList = cities;
    }

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }
}
