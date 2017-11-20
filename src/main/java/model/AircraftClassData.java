package model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "class_data")
public class AircraftClassData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "airClass")
    private Set<AircraftPlaceInfo> placeDataSet; //места соответствующие этому классу.


    public AircraftClassData() {
    }

    public AircraftClassData(String name) {
        this.name = name;
    }


    public Set<AircraftPlaceInfo> getPlaceDataSet() {
        return placeDataSet;
    }

    public AircraftClassData setPlaceDataSet(Set<AircraftPlaceInfo> placeDataSet) {
        this.placeDataSet = placeDataSet;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public AircraftClassData setId(Integer id) {
        this.id = id;
        return this;
    }

    public AircraftClassData setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return this.name;
    }
}
