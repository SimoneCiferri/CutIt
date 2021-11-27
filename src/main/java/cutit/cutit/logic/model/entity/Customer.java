package cutit.cutit.logic.model.entity;

public class Customer {

    private Integer id;
    private String username;
    private String pwd;
    private String email;

    public Customer(Integer id, String username, String pwd, String email){
        setId(id);
        setUsername(username);
        setPwd(pwd);
        setEmail(email);
    }

    public Integer getUserId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPwd(){
        return pwd;
    }

    public void setPwd(String pwd){
        this.pwd = pwd;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

}
