package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("tester")
public class Tester extends User{
    public Tester(String fullName, String username, String password) {
        super(fullName, username, password);
    }

    public Tester() {
        super();
    }
}
