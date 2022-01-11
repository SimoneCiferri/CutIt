package cutit.controller.login;

import cutit.bean.CustomerBean;
import cutit.bean.HairdresserBean;
import cutit.bean.firstui.CustomerBeanFirstUI;
import cutit.bean.firstui.HairdresserBeanFirstUI;
import cutit.decorator.ViewLayout;
import cutit.facade.Facade;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Objects;

public class SignUpViewController {

    private CustomerBean customerBeanFirstUI;
    private HairdresserBean hairdresserBeanFirstUI;
    private LoginController loginController;

    @FXML
    private TextField tfCustomerName, tfCustomerSurname, tfCustomerEmail, tfHairdresserName, tfHairdresserSurname, tfHairdresserEmail, tfHairdresserShopName, tfHairdresserPIVA;

    @FXML
    private PasswordField pfCustomerPassword, pfCustomerConfirmPassword, pfHairdresserPassword, pfHairdresserConfirmPassword;

    @FXML
    private ChoiceBox<String> cbCustomerGender;

    @FXML
    private DatePicker dpCustomerBirthdate;

    @FXML
    private Button btBack;

    @FXML
    public void initialize(){
        customerBeanFirstUI = new CustomerBeanFirstUI();
        hairdresserBeanFirstUI = new HairdresserBeanFirstUI();
        loginController = new LoginController();
        cbCustomerGender.getItems().addAll("Female", "Male", "Other");
        System.out.println("CONTROLLER GRAFICO SIGNUPVIEWCONTROLLER");
    }

    @FXML
    public boolean trySignUpCustomer(){
        try{
            if(checkSamePasswd(pfCustomerPassword, pfCustomerConfirmPassword) && !isSomethingNull(tfCustomerName.getText(), tfCustomerSurname.getText(),
                    cbCustomerGender.getValue(), tfCustomerEmail.getText(), pfCustomerPassword.getText())){
                customerBeanFirstUI.setcName(tfCustomerName.getText());
                customerBeanFirstUI.setcSurname(tfCustomerSurname.getText());
                customerBeanFirstUI.setcBirthDate(dpCustomerBirthdate.getValue());
                customerBeanFirstUI.setcGender(cbCustomerGender.getValue());
                customerBeanFirstUI.setcEmail(tfCustomerEmail.getText());
                customerBeanFirstUI.setcPassword(pfCustomerPassword.getText());
                if(loginController.signUpCustomer(this.customerBeanFirstUI)){
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
            if(checkSamePasswd(pfHairdresserPassword, pfHairdresserConfirmPassword) && !isSomethingNull(tfHairdresserName.getText(),
                    tfHairdresserSurname.getText(), tfHairdresserEmail.getText(), tfHairdresserPIVA.getText(), tfHairdresserShopName.getText())){
                hairdresserBeanFirstUI.sethName(tfHairdresserName.getText());
                hairdresserBeanFirstUI.sethSurname(tfHairdresserSurname.getText());
                hairdresserBeanFirstUI.sethEmail(tfHairdresserEmail.getText());
                hairdresserBeanFirstUI.setpIVA(tfHairdresserPIVA.getText());
                hairdresserBeanFirstUI.setShopName(tfHairdresserShopName.getText());
                hairdresserBeanFirstUI.sethPassword(pfHairdresserPassword.getText());
                if(loginController.signUpHair(hairdresserBeanFirstUI)){
                    Facade.getInstance().decorateView(ViewLayout.LOGIN);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    private boolean checkSamePasswd(PasswordField p1, PasswordField p2) {
        if(!Objects.equals(p1.getText(), "") && !Objects.equals(p2.getText(), "")){
            return Objects.equals(p1.getText(), p2.getText());
        }
        return false;
    }

    private Boolean isSomethingNull(String s1, String s2, String s3, String s4, String s5){
        return (Objects.equals(s1, "") || Objects.equals(s2, "") || Objects.equals(s3, "") || Objects.equals(s4, "") || Objects.equals(s5, ""));
    }

    @FXML
    private void goBack(){
        try {
            Facade.getInstance().decorateView(ViewLayout.LOGIN);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
