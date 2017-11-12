package model;

import javax.persistence.*;
import java.util.HashSet;
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

    public void setPlaceDataSet(Set<AircraftPlaceInfo> placeDataSet) {
        this.placeDataSet = placeDataSet;
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
