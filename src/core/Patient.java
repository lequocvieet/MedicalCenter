package core;

import java.util.Date;

public class Patient extends Person {

    private String doctorName;
    private String gender;

    public Patient() {

    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Patient(String id, String lastName, String firstName, Date dateOfBirth, String address, String email, String phoneNum, String doctorName,
                   String gender, WeightHeight[] patientWH) { // constructor day du tham so
        super(id, lastName, firstName, dateOfBirth, address, phoneNum, email, patientWH);
        this.gender = gender;
        this.doctorName = doctorName;
    }

    public Patient(String id, String lastName, String firstName, Date dateOfBirth, String address, String email, String phoneNum,
                   String gender, String doctorName) {  //constructor khuyet WH
        super(id, lastName, firstName, dateOfBirth, address, phoneNum, email);
        this.gender = gender;
        this.doctorName = doctorName;

    }


}
