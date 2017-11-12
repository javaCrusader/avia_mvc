package model;

import javax.persistence.*;

@Entity
@Table(name = "crew")
public class CrewMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vacation_id", nullable = false)
    private CrewMemberVacation vacation;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "function_id", nullable = false)
    private CompanyRole function;


    private int salaryInHour;


    public CrewMember() {
    }

    public CrewMember(String name, CompanyRole function, CrewMemberVacation vacation) {
        this.name = name;
        this.function = function;
        this.vacation = vacation;
    }


    public CompanyRole getFunction() {
        return function;
    }

    public void setFunction(CompanyRole function) {
        this.function = function;
    }

    public CrewMemberVacation getVacation() {
        return vacation;
    }

    public void setVacation(CrewMemberVacation vacation) {
        this.vacation = vacation;
    }

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

}
