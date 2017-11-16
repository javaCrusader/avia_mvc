package model;

import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private Date start;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private Date end;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "vacation")
    private CrewMember member;


    @Transient
    private static SimpleDateFormat dateFmt;

    {
        dateFmt = new SimpleDateFormat("yyyy-MM-dd");
    }

    public CrewMemberVacation() {

    }

    public static SimpleDateFormat getDateFmt() {
        return dateFmt;
    }

    public static void setDateFmt(SimpleDateFormat dateFmt) {
        CrewMemberVacation.dateFmt = dateFmt;
    }

    public CrewMemberVacation(Date start, Date end) {
        this.start = start;
        this.end = end;
    }

    public CrewMemberVacation(String start, String end) throws ParseException {
        setStringStart(start);
        setStringEnd(end);
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

    public void setStringStart(String start) throws ParseException {
        this.start = dateFmt.parse(start);
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public void setStringEnd(String end) throws ParseException {
        this.end = dateFmt.parse(end);
    }

}
