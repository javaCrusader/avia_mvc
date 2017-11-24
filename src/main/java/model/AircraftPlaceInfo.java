package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

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

    @Column(nullable = false)
    private int capacity;

    @Column(name = "price")
    private int price;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "airPlace")
    private List<Ticket> ticketList;

    public AircraftPlaceInfo() {

    }

    public AircraftPlaceInfo(Aircraft aircraft, AircraftClassData airClass, int capacity) {
        this.capacity = capacity;
        this.airClass = airClass;
        this.aircraft = aircraft;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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
        this.aircraft = aircraft;
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

    public AircraftPlaceInfo addTicket(Ticket ticket) {
        if (ticketList == null) {
            ticketList = new ArrayList<>();
        }
        ticketList.add(ticket.setAirPlace(this));
        ticket.setFactCost(this.price); // стоимость на момент покупки для возврата
        return this;
    }

    public AircraftPlaceInfo removeTicket(Integer idTicket) {
        Iterator<Ticket> itPlaceTicketList = ticketList.iterator();
        while (itPlaceTicketList.hasNext()) { //лямбы не работают с сущностями еклипслинка
            Ticket iter = itPlaceTicketList.next();
            if (iter.getId().intValue() == idTicket.intValue()) {
                itPlaceTicketList.remove();
            }
        }
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AircraftPlaceInfo record = (AircraftPlaceInfo) o;

        if (this.capacity != record.capacity) return false;
        if (this.price != record.price) return false;
        if (!this.airClass.equals(record.airClass)) return false;
        return this.id.intValue() == record.id.intValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, capacity, airClass);
    }

}
