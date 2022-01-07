package cutit.model;

public class User {

    private String userID;
    private String pwd;
    private Integer role;

    public User(){}

    public User(String userID, String pwd, Integer role){
        setUserID(userID);
        setPwd(pwd);
        setRole(role);
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

}
