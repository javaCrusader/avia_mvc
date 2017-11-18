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

    private int factCost; //стоимость на момент покупки

    public Ticket() {
    }

    public Ticket(Flight flight, User user) {
        this.user = user;
        this.flight = flight;
    }

    public int getFactCost() {
        return factCost;
    }

    public void setFactCost(int factCost) {
        this.factCost = factCost;
    }

    public AircraftPlaceInfo getAirPlace() {
        return airPlace;
    }

    public Ticket setAirPlace(AircraftPlaceInfo airPlace) {
        this.airPlace = airPlace;
        return this;
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

    public Ticket setFlight(Flight flight) {
        this.flight = flight;
        return this;
    }

}
