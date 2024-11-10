package model;
import java.util.Date;

public class Patient {

    private int id;
    private int cardIdentity;
    private String name;
    private String lastName;
    private Date admissionDate;

    public Patient(int id, int cardIdentity, String name, String lastName, Date admissionDate) {
        this.id = id;
        this.cardIdentity = cardIdentity;
        this.name = name;
        this.lastName = lastName;
        this.admissionDate = admissionDate;
    }
    

    public Patient(int cardIdentity, String name, String lastName, Date admissionDate) {
        this.cardIdentity = cardIdentity;
        this.name = name;
        this.lastName = lastName;
        this.admissionDate = admissionDate;
    }


    // GETTERS AND SETTERS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCardIdentity() {
        return cardIdentity;
    }

    public void setCardIdentity(int cardIdentity) {
        this.cardIdentity = cardIdentity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

}
