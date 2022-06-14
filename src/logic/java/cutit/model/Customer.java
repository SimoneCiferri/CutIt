package cutit.model;

import java.time.LocalDate;
import java.util.List;

public class Customer extends User{

    private String name;
    private String surname;
    private LocalDate birthDate;
    private String gender;
    private List<Appointment> bookedAppointments;
    private List<Promotion> promotions;


    public Customer(){}

    public Customer(String userID, String pwd, Integer role, String name, String surname, LocalDate birthDate, String gender){
        super(userID, pwd, role);
        setName(name);
        setSurname(surname);
        setBirthDate(birthDate);
        setGender(gender);
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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
