package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("programmer")
public class Programmer extends User{
    public Programmer(String fullName, String username, String password) {
        super(fullName, username, password);
    }

    public Programmer() {
        super();
    }


}
