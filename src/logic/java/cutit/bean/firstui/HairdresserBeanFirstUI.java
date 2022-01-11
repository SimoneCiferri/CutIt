package cutit.bean.firstui;

import cutit.bean.HairdresserBean;

public class HairdresserBeanFirstUI implements HairdresserBean {

    private String hEmail;
    private String hPassword;
    private Integer hRole;
    private String hName;
    private String hSurname;
    private String pIVA;
    private String shopName;

    public Integer gethRole() {
        return hRole;
    }

    public void sethRole(Integer hRole) {
        this.hRole = hRole;
    }

    public String gethName() {
        return hName;
    }

    public void sethName(String hName) {
        this.hName = hName;
    }

    public String gethSurname() {
        return hSurname;
    }

    public void sethSurname(String hSurname) {
        this.hSurname = hSurname;
    }

    public String gethEmail() {
        return hEmail;
    }

    public void sethEmail(String hEmail) {
        this.hEmail = hEmail;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getpIVA() {
        return pIVA;
    }

    public void setpIVA(String pIVA) {
        this.pIVA = pIVA;
    }

    public String gethPassword() {
        return hPassword;
    }

    public void sethPassword(String hPassword) {
        this.hPassword = hPassword;
    }

}
