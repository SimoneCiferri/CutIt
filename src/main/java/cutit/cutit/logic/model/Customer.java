package cutit.cutit.logic.model;

import java.util.List;

public class Customer extends User{

    private String name;
    private String surname;
    private Integer age;
    private String Gender;
    private List<Appointment> bookedAppointments; //lista di appuntamenti prenotati passati e futuri

    public Customer(){
        super();
    }

    public Customer(String userID, String pwd, Integer role, String name, String surname, Integer age, String gender){
        super(userID, pwd, role);
        setName(name);
        setSurname(surname);
        setAge(age);
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

}
