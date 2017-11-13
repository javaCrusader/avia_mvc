package model;

import javax.persistence.*;

@Entity
@Table(name = "place_data")
public class AircraftPlaceInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "aircraft_id", nullable = false)
    private Aircraft aircraft;

    @ManyToOne
    @JoinColumn(name = "airclass_id", nullable = false)
    private AircraftClassData airClass;


    @Column (nullable = false)
    private int capacity;

    public AircraftPlaceInfo() {

    }

    public AircraftPlaceInfo(Aircraft aircraft, AircraftClassData airClass, int capacity) {
        this.capacity = capacity;
        this.airClass = airClass;
        this.aircraft = aircraft;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * каждому виду мест свой самолет.
     */
    public Aircraft getAircraft() {
        return aircraft;
    }

    public AircraftPlaceInfo setAircraft(Aircraft aircraft) {
        this.aircraft=aircraft;
        return this;
    }

    /**
     * каждому месту соответствует класс.
     * а каждому классу соответствует много мест на различных самолетах.
     */
    public AircraftClassData getAirClass() {
        return airClass;
    }

    public AircraftPlaceInfo setAirClass(AircraftClassData airClass) {
        this.airClass = airClass;
        return this;
    }

    public int getCapacity() {
        return capacity;
    }

    public AircraftPlaceInfo setCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }
}
