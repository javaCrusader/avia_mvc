package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "aircrafts")
public class Aircraft {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aircraft")
    private List<AircraftPlaceInfo> placeInfoList;

    public Aircraft() {
    }


    public Aircraft(String name) {
        this.name = name;
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
}
