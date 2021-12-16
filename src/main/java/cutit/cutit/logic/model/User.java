package cutit.cutit.logic.model;

public class User {

    private Integer id;
    private String username;
    private String pwd;

    public User(){}

    public User(Integer id, String username, String pwd){
        setId(id);
        setUsername(username);
        setPwd(pwd);
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
