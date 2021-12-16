package cutit.cutit.logic.model;

public class Hairdresser extends User{

    private String name;
    private String surname;
    private String pIVA;

    public Hairdresser(){
        super();
    }

    public Hairdresser(String userID, String pwd, Integer role, String name, String surname, String pIVA){
        super(userID, pwd, role);
        setName(name);
        setSurname(surname);
        setpIVA(pIVA);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getpIVA() {
        return pIVA;
    }

    public void setpIVA(String pIVA) {
        this.pIVA = pIVA;
    }
}