package core;

import java.util.Date;

public class Person {  // thong nhat la class bed se khong gan voi benh nhan ma chi la mot thuoc tinh ben ngoai
    // tam thoi the da neu can se sua lai trong peerson va database them Bed vao

    protected String ID; // khoa chinh de tim theo Id
    protected String lastName;
    protected String firstName;
    protected Date dateOfBirth;
    protected String address;
    protected String phoneNum;
    protected String email;
    protected WeightHeight[] wH;


    public Person(String iD, String lastName, String firstName, Date dateOfBirth, String address, String phoneNum,
                  String email) {
        super();         // constructor khuyet wH
        ID = iD;
        this.lastName = lastName;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phoneNum = phoneNum;
        this.email = email;
        this.wH = null;     // set wH =null
    }

    public Person() {   // constructor khong tham so
        super();
    }

    public Person(String iD, String lastName, String firstName, Date dateOfBirth, String address, String phoneNum,
                  String email, WeightHeight[] wH) {
        super();                     // constructor day du tham so
        ID = iD;
        this.lastName = lastName;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phoneNum = phoneNum;
        this.email = email;
        this.wH = wH;
    }

    public String getID() {
        return ID;
    }

    public void setID(String iD) {
        ID = iD;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public WeightHeight[] getwH() {
        return wH;
    }

    public void setwH(WeightHeight[] wH) {
        this.wH = wH;
    }


}
