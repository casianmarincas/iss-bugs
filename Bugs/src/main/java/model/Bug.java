package model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Objects;

@javax.persistence.Entity
@Table(name="bugs")
public class Bug extends Entity<Long> {
    private String name;

    @ManyToOne
    @JoinColumn(name="tester_id", nullable = false)
    private Tester tester;

    private String project;

    private String description;

    private String status;

    public Bug(String name, Tester tester, String project, String description) {
        this.name = name;
        this.tester = tester;
        this.project = project;
        this.description = description;
        this.status = "unresolved";
    }

    public Bug() {
    }

    public String getName() {
        return name;
    }

    public Tester getTester() {
        return tester;
    }

    public void setTester(Tester tester) {
        this.tester = tester;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bug bug = (Bug) o;
        return name.equals(bug.name) && tester.equals(bug.tester) && project.equals(bug.project) && description.equals(bug.description) && status.equals(bug.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, tester, project, description, status);
    }
}

