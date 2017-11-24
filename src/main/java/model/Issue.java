package model;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "issues")
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String problem;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    private boolean closed;

    public Issue(User user, String problem) {
        this.user = user;
        this.problem = problem;
    }

    public Issue() {
    }

    public boolean isClosed() {
        return closed;
    }

    public Issue setClosed(boolean closed) {
        this.closed = closed;
        return this;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Issue record = (Issue) o;
        if (this.closed != record.closed) return false;
        if (this.created.getTime() != record.getCreated().getTime()) return false;
        return this.problem.equals(record.problem) && this.id.intValue() == record.id.intValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, problem,created,closed);
    }
}
