package model;

import org.eclipse.persistence.jpa.jpql.parser.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "flight")
    private List<Ticket> ticketList;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date start;


    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date end;


    @ManyToOne
    @JoinColumn(name = "start_city_id")
    private City startCity;

    @ManyToOne
    @JoinColumn(name = "end_city_id")
    private City endCity;

    //cascade = CascadeType.ALL, mappedBy = "flight"
    @OneToMany
    @JoinTable(name = "flight_crew", joinColumns = @JoinColumn(name = "flight_id"), inverseJoinColumns = @JoinColumn(name = "member_id"))
    private List<CrewMember> crewMemberList;

    @OneToOne
    @JoinColumn(name = "aircraft_id", nullable = false)
    private Aircraft aircraft;

    private boolean isDone;


    @Transient
    private SimpleDateFormat dateFmt;

    {
        dateFmt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
    }

    public List<CrewMember> getCrewMemberList() {
        return crewMemberList;
    }

    public void setCrewMemberList(List<CrewMember> crewMemberList) {
        this.crewMemberList = crewMemberList;
    }

    public City getStartCity() {
        return startCity;
    }

    public Flight setStartCity(City startCity) {
        this.startCity = startCity;
        return this;
    }

    public City getEndCity() {
        return endCity;
    }

    public Flight setEndCity(City endCity) {
        this.endCity = endCity;
        return this;
    }

    public Flight setStartEndCity(City startCity,City endCity) {
        this.startCity = startCity;
        this.endCity = endCity;
        return this;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }


    public SimpleDateFormat getDateFmt() {
        return dateFmt;
    }
}
