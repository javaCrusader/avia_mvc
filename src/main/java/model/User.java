package model;


import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;

    private String password;

    private String firstName;

    private String lastName;

    @NumberFormat
    private String phoneNumber;

    private String address;

    private int balance;

    private long passport;

    @NumberFormat
    private String creditCard;

    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    private String email;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    private List<Ticket> ticketsList;

    @ManyToMany
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roleList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    private List<Issue> issueList;

    public User() {
    }

    public User(String username, String password, List<Role> roles) {
        this.name = username;
        this.password = password;
        this.roleList = roles;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public int getBalance() {
        return balance;
    }

    public long getPassport() {
        return passport;
    }

    public void setPassport(long passport) {
        this.passport = passport;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Ticket> getTicketsList() {
        return ticketsList;
    }

    public void setTicketsList(List<Ticket> ticketsList) {
        this.ticketsList = ticketsList;
    }

    public List<Issue> getIssueList() {
        return issueList;
    }

    public void setIssueList(List<Issue> issueList) {
        this.issueList = issueList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roles) {
        this.roleList = roles;
    }

    public User addTicket(Ticket ticket) {
        if (ticketsList == null) {
            ticketsList = new ArrayList<>();
        }
        ticketsList.add(ticket.setUser(this));
        return this;
    }

    public User removeTicket(Integer idTicket) {
        Iterator<Ticket> itPlaceTicketList = ticketsList.iterator();
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

        User record = (User) o;

        if (this.passport != record.getPassport()) return false;
        if (!this.firstName.equals(record.firstName)) return false;
        if (!this.lastName.equals(record.lastName)) return false;
        if (!this.email.equals(record.email)) return false;
        if (!this.creditCard.equals(record.creditCard)) return false;
        if (this.roleList.size() != record.roleList.size())
            return false;
        else {
            int i = 0;
            for (Role role : roleList) {
                if (!role.equals(record.roleList.get(i)))
                    return false;
                i++;
            }
        }
        if (this.issueList.size() != record.issueList.size())
            return false;
        else {
            int i = 0;
            for (Issue issue : issueList) {
                if (!issue.equals(record.issueList.get(i)))
                    return false;
                i++;
            }
        }

        return this.id.intValue() == record.id.intValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, firstName, lastName, passport, creditCard, email);
    }


}
