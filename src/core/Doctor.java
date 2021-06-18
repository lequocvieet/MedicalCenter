package core;

import java.util.Date;

public class Doctor extends Person {

    public Doctor() {
        super();
    }
    public Doctor(String id, String lastName, String firstName, Date dateOfBirth, String address, String email, String phoneNum) {
        super(id, lastName, firstName, dateOfBirth, address, phoneNum, email);
    }


}
