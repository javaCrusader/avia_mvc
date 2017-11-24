package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "crew")
public class CrewMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    /*поле необходимо писать в БД с каждым новым членом команды */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vacation_id", nullable = false)
    private CrewMemberVacation vacation;

    /*это напротив не надо никогда писать */
    @ManyToOne
    @JoinColumn(name = "function_id", nullable = false)
    private CompanyRole function;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "flight_crew", joinColumns = @JoinColumn(name = "member_id"), inverseJoinColumns = @JoinColumn(name = "flight_id"))
    private Flight flight;

    private int salaryInHour;


    public CrewMember() {
    }

    public CrewMember(String name, CompanyRole function, CrewMemberVacation vacation) {
        this.name = name;
        this.function = function;
        this.vacation = vacation;
    }

    public Flight getFlight() {
        return flight;
    }

    public CrewMember setFlight(Flight flight) {
        this.flight = flight;
        return this;
    }

    public int getSalaryInHour() {
        return salaryInHour;
    }

    public void setSalaryInHour(int salaryInHour) {
        this.salaryInHour = salaryInHour;
    }

    public CompanyRole getFunction() {
        return function;
    }

    public CrewMember setFunction(CompanyRole function) {
        this.function = function;
        return this;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CrewMember record = (CrewMember) o;

        if (this.name.equals(record.name)) return false;
        if (!this.vacation.equals(record.vacation)) return false;
        if (!this.function.equals(record.function)) return false;
        if (this.salaryInHour != record.salaryInHour) return false;
        return this.id.intValue() == record.id.intValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, vacation, function,salaryInHour);
    }

}
