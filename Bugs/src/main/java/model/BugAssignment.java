package model;

import org.hibernate.annotations.Cascade;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@javax.persistence.Entity
@Table(name="bugs_assignments")
public class BugAssignment extends Entity<Long> {


    @ManyToOne
    @JoinColumn(name = "bug_id", nullable = false)
    private Bug bug;

    @ManyToOne
    @JoinColumn(name = "programmer_id", nullable = false)
    private Programmer programmer;

    public BugAssignment(Bug bug, Programmer programmer) {
        this.bug = bug;
        this.programmer = programmer;
    }

    public BugAssignment() {
    }

    public Bug getBug() {
        return bug;
    }

    public void setBug(Bug bug) {
        this.bug = bug;
    }

    public Programmer getProgrammer() {
        return programmer;
    }

    public void setProgrammer(Programmer programmer) {
        this.programmer = programmer;
    }

}
