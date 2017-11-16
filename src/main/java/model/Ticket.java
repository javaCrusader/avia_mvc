package model;

import javax.persistence.*;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "place_info_id")
    private AircraftPlaceInfo airPlace;

    private String firstName;

    private String surName;

    private String lastName;

    private long passport;

    public Ticket() {
    }

    public Ticket(Flight flight, User user) {
        this.user = user;
        this.flight = flight;
    }

    public AircraftPlaceInfo getAirPlace() {
        return airPlace;
    }

    public void setAirPlace(AircraftPlaceInfo airPlace) {
        this.airPlace = airPlace;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getPassport() {
        return passport;
    }

    public void setPassport(long passport) {
        this.passport = passport;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public Ticket setUser(User userData) {
        this.user = userData;
        return this;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

}
