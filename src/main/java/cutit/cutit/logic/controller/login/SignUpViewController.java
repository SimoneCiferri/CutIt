package cutit.cutit.logic.controller.login;

import cutit.cutit.logic.bean.CustomerBean;
import cutit.cutit.logic.bean.HairdresserBean;
import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.facade.Facade;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.Objects;

public class SignUpViewController {

    private CustomerBean customerBean;
    private HairdresserBean hairdresserBean;
    private LoginController loginController;

    @FXML
    private TextField tfCustomerName, tfCustomerSurname, tfCustomerAge, tfCustomerEmail, tfHairdresserName, tfHairdresserSurname, tfHairdresserEmail, tfHairdresserShopName, tfHairdresserPIVA;

    @FXML
    private PasswordField pfCustomerPassword, pfCustomerConfirmPassword, pfHairdresserPassword, pfHairdresserConfirmPassword;

    @FXML
    private ChoiceBox cbCustomerGender;

    @FXML
    public void initialize(){
        customerBean = new CustomerBean();
        hairdresserBean = new HairdresserBean();
        loginController = new LoginController();
        System.out.println("CONTROLLER GRAFICO SIGNUPVIEWCONTROLLER");
    }

    @FXML
    public boolean trySignUpCustomer(){
        try{
            if(checkSamePswd(pfCustomerPassword, pfCustomerConfirmPassword) && !isSomethingNull(tfCustomerName.getText(), tfCustomerSurname.getText(),
                    "Female", tfCustomerEmail.getText(), pfCustomerPassword.getText())){
                customerBean.setcName(tfCustomerName.getText());
                customerBean.setcSurname(tfCustomerSurname.getText());
                customerBean.setcAge(25);
                customerBean.setcGender("Female");
                customerBean.setcEmail(tfCustomerEmail.getText());
                customerBean.setcPassword(pfCustomerPassword.getText());
                if(loginController.signUpCustomer(this.customerBean)){
                    Facade.getInstance().decorateView(ViewLayout.LOGIN);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    @FXML
    public boolean trySignUpHair(){
        try{
            if(checkSamePswd(pfHairdresserPassword, pfHairdresserConfirmPassword) && !isSomethingNull(tfHairdresserName.getText(),
                    tfHairdresserSurname.getText(), tfHairdresserEmail.getText(), tfHairdresserPIVA.getText(), tfHairdresserShopName.getText())){
                hairdresserBean.sethName(tfHairdresserName.getText());
                hairdresserBean.sethSurname(tfHairdresserSurname.getText());
                hairdresserBean.sethEmail(tfHairdresserEmail.getText());
                hairdresserBean.setpIVA(tfHairdresserPIVA.getText());
                hairdresserBean.setShopName(tfHairdresserShopName.getText());
                hairdresserBean.sethPassword(pfHairdresserPassword.getText());
                if(loginController.signUpHair(hairdresserBean)){
                    Facade.getInstance().decorateView(ViewLayout.LOGIN);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    private boolean checkSamePswd(PasswordField p1, PasswordField p2) {
        if(!Objects.equals(p1.getText(), "") && !Objects.equals(p2.getText(), "")){
            return Objects.equals(p1.getText(), p2.getText());
        }
        return false;
    }

    private Boolean isSomethingNull(String s1, String s2, String s3, String s4, String s5){
        return (Objects.equals(s1, "") || Objects.equals(s2, "") || Objects.equals(s3, "") || Objects.equals(s4, "") || Objects.equals(s5, ""));
    }

}
