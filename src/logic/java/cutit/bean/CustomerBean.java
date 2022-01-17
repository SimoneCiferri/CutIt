package cutit.bean;

import java.time.LocalDate;
import java.util.List;

public interface CustomerBean {

    String getcEmail();

    void setcEmail(String cEmail);

    String getcPassword();

    void setcPassword(String cPassword);

    Integer getcRole();

    void setcRole(Integer cRole);

    String getcName();

    void setcName(String cName);

    String getcSurname();

    void setcSurname(String cSurname);

    LocalDate getcBirthDate();

    void setcBirthDate(LocalDate cBirthDate);

    String getcGender();

    void setcGender(String cGender);

    List<String> getAllPersonalPromotions();

    void setAllPersonalPromotions(List<String> allPromotions);

    List<AppointmentBean> getAllBookedAppointments();

    void setAllBookedAppointments(List<AppointmentBean> allBookedAppointments);



}
