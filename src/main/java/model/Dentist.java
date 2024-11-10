package model;

public class Dentist {

    private int id;
    private int registration;
    private String name;
    private String lastName;

    //CONSTRCTOR PARA CREAR EL OBJETO
    public Dentist(int id, int registration, String name, String lastName){
        this.id = id;
        this.registration = registration;
        this.name = name;
        this.lastName = lastName;
    }

    //CONSTRUCTOR PARA CONSULTAR EN LA DB
    public Dentist(int registration, String name, String lastName){
        this.registration = registration;
        this.name = name;
        this.lastName = lastName;
    }

    //CONSTRUCTOR PARA TEST
    public Dentist(){

    }

    //SETTERS Y GETTERS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRegistration() {
        return registration;
    }

    public void setRegistration(int registration) {
        this.registration = registration;
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

    

}
