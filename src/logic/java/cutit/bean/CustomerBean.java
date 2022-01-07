package cutit.bean;

import java.time.LocalDate;
import java.util.List;

public class CustomerBean {

    private String cEmail;
    private String cPassword;
    private Integer cRole;
    private String cName;
    private String cSurname;
    private LocalDate cBirthDate;
    private String cGender;
    private List<String> allBookedAppointments;
    private List<String> allPromotions;
    private List<AppData> allBookedAppointment;

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

    public List<String> getAllBookedAppointments() {
        return allBookedAppointments;
    }

    public void setAllBookedAppointments(List<String> allBookedAppointments) {
        this.allBookedAppointments = allBookedAppointments;
    }

    public List<String> getAllPromotions() {
        return allPromotions;
    }

    public void setAllPromotions(List<String> allPromotions) {
        this.allPromotions = allPromotions;
    }

    public List<AppData> getAllBookedAppointment() {
        return allBookedAppointment;
    }

    public void setAllBookedAppointment(List<AppData> allBookedAppointment) {
        this.allBookedAppointment = allBookedAppointment;
    }

    public static class AppData{
        String appStarTime;
        String appEndTime;
        String appCustomer;
        String appService;
        String appPromotion;
        String appShopName;

        public String getAppStarTime() {
            return appStarTime;
        }

        public void setAppStarTime(String appStarTime) {
            this.appStarTime = appStarTime;
        }

        public String getAppEndTime() {
            return appEndTime;
        }

        public void setAppEndTime(String appEndTime) {
            this.appEndTime = appEndTime;
        }

        public String getAppCustomer() {
            return appCustomer;
        }

        public void setAppCustomer(String appCustomer) {
            this.appCustomer = appCustomer;
        }

        public String getAppService() {
            return appService;
        }

        public void setAppService(String appService) {
            this.appService = appService;
        }

        public String getAppPromotion() {
            return appPromotion;
        }

        public void setAppPromotion(String appPromotion) {
            this.appPromotion = appPromotion;
        }

        public String getAppShopName() {
            return appShopName;
        }

        public void setAppShopName(String appShopName) {
            this.appShopName = appShopName;
        }
    }

}
