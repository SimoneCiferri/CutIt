package cutit.cutit.logic.controller.login;

import cutit.cutit.logic.bean.CustomerBean;
import cutit.cutit.logic.bean.HairdresserBean;
import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.facade.Facade;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
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
            if(checkSamePswd()){
                customerBean.setName(tfCustomerName.getText());
                customerBean.setSurname(tfCustomerSurname.getText());
                customerBean.setAge(25);
                customerBean.setGender("Female");
                customerBean.setEmail(tfCustomerEmail.getText());
                customerBean.setPassword(pfCustomerPassword.getText());
                if(loginController.signUpCustomer(this.customerBean)){
                    Facade.getInstance().decorateView(ViewLayout.LOGIN);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    private boolean checkSamePswd() {
        if(!Objects.equals(pfCustomerPassword.getText(), "") && !Objects.equals(pfCustomerConfirmPassword.getText(), "")){
            return Objects.equals(pfCustomerPassword.getText(), pfCustomerConfirmPassword.getText());
        }
        return false;
    }

    @FXML
    public boolean trySignUpHair(){
        try{
            if(checkSamePswd()){
                hairdresserBean.setName(tfHairdresserName.getText());
                hairdresserBean.setSurname(tfHairdresserSurname.getText());
                hairdresserBean.setEmail(tfHairdresserEmail.getText());
                hairdresserBean.setpIVA(tfHairdresserPIVA.getText());
                hairdresserBean.setShopName(tfHairdresserShopName.getText());
                hairdresserBean.setPassword(pfHairdresserPassword.getText());
            }
            if(loginController.signUpHair(hairdresserBean)){
                Facade.getInstance().decorateView(ViewLayout.LOGIN);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

}
