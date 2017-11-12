package model;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "vacations")
public class CrewMemberVacation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(TemporalType.DATE)
    @Column (nullable = false)
    private Date start;

    @Temporal(TemporalType.DATE)
    @Column (nullable = false)
    private Date end;

    @OneToOne(mappedBy = "vacation")
    private CrewMember member;

    @Transient
    private SimpleDateFormat dateFmt;
    {
        dateFmt = new SimpleDateFormat("dd/M/yyyy");
    }

    public CrewMemberVacation() {

    }

    public CrewMemberVacation(Date start, Date end) {
        this.start = start;
        this.end = end;
    }

    public CrewMemberVacation(String start, String end) throws ParseException {
        setStart(start);
        setEnd(end);
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

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public void setStart(String start) throws ParseException {
        this.start = dateFmt.parse(start);
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public void setEnd(String end) throws ParseException {
        this.end = dateFmt.parse(end);
    }


}
