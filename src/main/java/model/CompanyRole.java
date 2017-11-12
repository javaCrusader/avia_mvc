package model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "company_roles")
public class CompanyRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "function")
    private Set<CrewMember> crewMemberSet;

    @Column(unique = true, nullable = false)
    private String name;

    public CompanyRole() {

    }

    public CompanyRole(String name) {
        this.name = name;
    }

    public Set<CrewMember> getCrewMemberSet() {
        return crewMemberSet;
    }

    public void setCrewMemberSet(Set<CrewMember> crewMemberSet) {
        this.crewMemberSet = crewMemberSet;
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
