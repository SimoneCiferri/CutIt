package cutit.cutit.logic.model;

public class Customer extends User{

    private String email;

    public Customer(){
        super();
    }

    public Customer(Integer id, String username, String pwd ,String email){
        super(id, username, pwd);
        setEmail(email);
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

}
