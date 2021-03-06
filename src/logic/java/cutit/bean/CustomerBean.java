package cutit.bean;

import cutit.bean.interfaces.AppointmentBeanInterface;
import cutit.bean.interfaces.CustomerBeanInterface;

import java.time.LocalDate;
import java.util.List;

public class CustomerBean implements CustomerBeanInterface {

    private String cEmail;
    private String cPassword;
    private Integer cRole;
    private String cName;
    private String cSurname;
    private LocalDate cBirthDate;
    private String cGender;
    private List<String> allPersonalPromotions;
    private List<AppointmentBeanInterface> allBookedAppointmentBeanFirstUIS;

    public Integer getcRole() {
        return cRole;
    }

    public void setcRole(Integer cRole) {
        this.cRole = cRole;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcSurname() {
        return cSurname;
    }

    public void setcSurname(String cSurname) {
        this.cSurname = cSurname;
    }

    public LocalDate getcBirthDate() {
        return cBirthDate;
    }

    public void setcBirthDate(LocalDate cBirthDate) {
        this.cBirthDate = cBirthDate;
    }

    public String getcGender() {
        return cGender;
    }

    public void setcGender(String cGender) {
        this.cGender = cGender;
    }

    public String getcEmail() {
        return cEmail;
    }

    public void setcEmail(String cEmail) {
        this.cEmail = cEmail;
    }

    public String getcPassword() {
        return cPassword;
    }

    public void setcPassword(String cPassword) {
        this.cPassword = cPassword;
    }

    public List<String> getAllPersonalPromotions() {
        return allPersonalPromotions;
    }

    public void setAllPersonalPromotions(List<String> allPromotions) {
        this.allPersonalPromotions = allPromotions;
    }

    public List<AppointmentBeanInterface> getAllBookedAppointments() {
        return allBookedAppointmentBeanFirstUIS;
    }

    public void setAllBookedAppointments(List<AppointmentBeanInterface> allBookedAppointments) {
        allBookedAppointmentBeanFirstUIS = allBookedAppointments;
    }


}
