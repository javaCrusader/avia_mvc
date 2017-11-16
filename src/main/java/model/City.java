package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "city_list")
public class City {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "startCity")
    List<Flight> startFlightList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "endCity")
    List<Flight> endFlightList;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    private double latitude;

    private double longitude;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<Flight> getStartFlightList() {
        return startFlightList;
    }

    public void setStartFlightList(List<Flight> startFlightList) {
        this.startFlightList = startFlightList;
    }

    public List<Flight> getEndFlightList() {
        return endFlightList;
    }

    public void setEndFlightList(List<Flight> endFlightList) {
        this.endFlightList = endFlightList;
    }

}
