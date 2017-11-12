package model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "aircrafts")
public class Aircraft {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aircraft")
    private List<AircraftPlaceInfo> placeInfoSet;

    public Aircraft() {
    }


    public Aircraft(String name) {
        this.name = name;
    }


    /**
     * каждому самолету соответствуют все его места в различных классах.
     */
    public List<AircraftPlaceInfo> getPlaceInfoList() {
        return placeInfoSet;
    }


    public void setPlaceInfoList(List<AircraftPlaceInfo> placeInfoSet) {
        this.placeInfoSet = placeInfoSet;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}
