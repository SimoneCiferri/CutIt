package cutit.bean;

import cutit.bean.interfaces.UserBeanInterface;

public class UserBean implements UserBeanInterface {

    private String username;
    private String passwd;
    private Integer role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

}
