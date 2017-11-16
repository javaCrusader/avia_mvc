package model;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "issues")
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String problem;

    @Temporal(TemporalType.DATE)
    private Date created;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public Issue(User user, String problem) {
        this.user = user;
        this.problem = problem;
    }

    public Issue() {
    }


    @Transient
    private SimpleDateFormat dateFmt;

    {
        dateFmt = new SimpleDateFormat("dd/M/yyyy H:mm");
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

    public void setUser(User user) {
        this.user = user;
    }


    public SimpleDateFormat getDateFmt() {
        return dateFmt;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }
}
