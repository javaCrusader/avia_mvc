package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "aircrafts")
public class Aircraft {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aircraft")
    private List<AircraftPlaceInfo> placeInfoList;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "aircraft")
    private Flight flight;

    public Aircraft() {
    }


    public Aircraft(String name) {
        this.name = name;
    }


    public Flight getFlight() {
        return flight;
    }

    public Aircraft setFlight(Flight flight) {
        this.flight = flight;
        return this;
    }

    /**
     * каждому самолету соответствуют все его места в различных классах.
     */
    public List<AircraftPlaceInfo> getPlaceInfoList() {
        return placeInfoList;
    }


    public Aircraft setPlaceInfoList(List<AircraftPlaceInfo> placeInfoSet) {
        this.placeInfoList = placeInfoSet;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public Aircraft setId(Integer id) {
        this.id = id;
        return this;
    }

    public Aircraft setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public Aircraft addPlaceInfo(AircraftPlaceInfo placeInfo) {
        if (placeInfoList == null) { // лямбды к сожалению в еклипслинке ломают связывание
            placeInfoList = new ArrayList<>();
        }
        placeInfoList.add(placeInfo.setAircraft(this));
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Aircraft record = (Aircraft) o;

        if (!this.getName().equals(record.name)) return false;
        if (this.placeInfoList.size() != record.placeInfoList.size())
            return false;
        else {
            int i = 0;
            for (AircraftPlaceInfo place : placeInfoList) {
                if (!place.equals(record.placeInfoList.get(i)))
                    return false;
                i++;
            }
        }
        return this.id.intValue() == record.id.intValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, placeInfoList, flight);
    }

}
