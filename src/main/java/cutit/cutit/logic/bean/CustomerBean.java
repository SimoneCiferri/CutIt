package cutit.cutit.logic.bean;

public class CustomerBean {

    private String cEmail;
    private String cPassword;
    private Integer cRole;
    private String cName;
    private String cSurname;
    private Integer cAge;
    private String cGender;

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

    public Integer getcAge() {
        return cAge;
    }

    public void setcAge(Integer cAge) {
        this.cAge = cAge;
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

}
