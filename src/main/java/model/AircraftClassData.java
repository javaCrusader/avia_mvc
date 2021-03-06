package model;

import javax.persistence.*;
import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AircraftClassData record = (AircraftClassData) o;

        if (!this.name.equals(record.name)) return false;
        return this.id.intValue() == record.id.intValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, placeDataSet);
    }
}
