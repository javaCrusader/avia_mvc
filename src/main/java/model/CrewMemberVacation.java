package model;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "vacations")
public class CrewMemberVacation {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    @javax.persistence.Convert(converter = conversion.CustomLocalDateConverter.class)
    private LocalDate start;

    @Basic
    @javax.persistence.Convert(converter = conversion.CustomLocalDateConverter.class)
    private LocalDate end;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "vacation")
    private CrewMember member;


    public CrewMemberVacation() {

    }


    public CrewMemberVacation(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }


    public CrewMember getMember() {
        return member;
    }

    public void setMember(CrewMember member) {
        this.member = member;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

}
