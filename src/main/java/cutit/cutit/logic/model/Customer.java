package cutit.cutit.logic.model;

import java.util.List;

public class Customer extends User{

    private String name;
    private String surname;
    private Integer age;
    private String Gender;
    private List<Appointment> bookedAppointments; //lista di appuntamenti prenotati passati e futuri
    private List<Promotion> promotions; //Lista di promotion disponibili e usate per l'utente customer


    public Customer(){}

    public Customer(String userID, String pwd, Integer role, String name, String surname, Integer age, String gender){
        super(userID, pwd, role);
        setName(name);
        setSurname(surname);
        setAge(age);
        setGender(gender);
    }

    public Customer(String userID, String pwd, Integer role, String name, String surname, Integer age, String gender, List<Appointment> bookedAppointments, List<Promotion> promotions){
        super(userID, pwd, role);
        setName(name);
        setSurname(surname);
        setAge(age);
        setGender(gender);
        setBookedAppointments(bookedAppointments);
        setPromotions(promotions);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public List<Appointment> getBookedAppointments() {
        return bookedAppointments;
    }

    public void setBookedAppointments(List<Appointment> bookedAppointments) {
        this.bookedAppointments = bookedAppointments;
    }

    public List<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }

}
