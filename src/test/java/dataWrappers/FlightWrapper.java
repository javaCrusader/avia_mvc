package dataWrappers;

import model.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FlightWrapper {

    private List<Flight> flightList = new ArrayList<>();

    public List<Flight> getFlightList() {
        return flightList;
    }

    public void setFlightList(List<Flight> flightList) {
        this.flightList = flightList;
    }

    public static City releaseCity(double latitude, double longitude, String name, Country country) {
        City startCity = new City();
        startCity.setLatitude(latitude);
        startCity.setLongitude(longitude);
        startCity.setName(name);
        startCity.setCountry(country);
        return startCity;
    }

    public static AircraftPlaceInfo releasePlace(int capacity, int price, AircraftClassData classData, Aircraft aircraft) {
        AircraftPlaceInfo placeInfo = new AircraftPlaceInfo();
        placeInfo.setCapacity(capacity);
        placeInfo.setPrice(price);
        placeInfo.setAirClass(classData);
        placeInfo.setAircraft(aircraft);
        return placeInfo;
    }

    public static CrewMember releaseMember(Flight flight, int salary, String name, CrewMemberVacation vacation, CompanyRole companyRole) {
        CrewMember member = new CrewMember();
        member.setFlight(flight);
        member.setSalaryInHour(salary);
        member.setName(name);
        member.setVacation(vacation);
        member.setFunction(companyRole);
        return member;
    }


    public boolean contentEquals(FlightWrapper that) {
        Iterator<Flight> thisIterator = this.flightList.iterator();
        Iterator<Flight> thatIterator = that.flightList.iterator();
        while (thisIterator.hasNext() && thatIterator.hasNext()) {
            Flight expected = thisIterator.next();
            Flight actual = thatIterator.next();
            if (!expected.equals(actual)) {
                return false;
            }
        }
        return !(thisIterator.hasNext() || thatIterator.hasNext());
    }

}
