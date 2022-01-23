package cutit.utils;

import cutit.factory.AlertFactory;
import javafx.scene.control.Alert;

import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextFieldCheck {

    private static final String INFORMATION_TITLE = "Information";

    private TextFieldCheck(){

    }

    public static boolean isNumeric(String strNum, String title, String headerText, String contentText) {
        if (strNum == null) {
            return false;
        }
        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            Alert alert = AlertFactory.getInstance().generateAlert(Alert.AlertType.INFORMATION, title, headerText, contentText);
            alert.showAndWait();
            return false;
        }
        return true;
    }

    public static boolean isInteger(String strNum, String title, String headerText, String contentText) {
        if (strNum == null) {
            return false;
        }
        try {
            Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            Alert alert = AlertFactory.getInstance().generateAlert(Alert.AlertType.INFORMATION, title, headerText, contentText);
            alert.showAndWait();
            return false;
        }
        return true;
    }

    public static boolean isPhoneNumber(String strNum, String title, String headerText, String contentText) {
        if (strNum == null) {
            return false;
        }else{
            Pattern p = Pattern.compile("^\\d{10}$");
            Matcher m = p.matcher(strNum);
            if(m.matches()){
                return true;
            }else{
                Alert alert = AlertFactory.getInstance().generateAlert(Alert.AlertType.INFORMATION, title, headerText, contentText);
                alert.showAndWait();
                return false;
            }

        }
    }

    public static boolean isEmailAddress(String email) {
        if (email == null) {
            return false;
        }else{
            Pattern p = Pattern.compile("^(.+)@(.+)$");
            Matcher m = p.matcher(email);
            if(m.matches()){
                return true;
            }else{
                Alert alert = AlertFactory.getInstance().generateAlert(Alert.AlertType.INFORMATION, INFORMATION_TITLE, "Invalid Email", "Please enter a valid email address.");
                alert.showAndWait();
                return false;
            }

        }
    }

    public static boolean isPiva(String piva) {
        if (piva == null) {
            return false;
        }else{
            Pattern p = Pattern.compile("^[0-9]{11}$");
            Matcher m = p.matcher(piva);
            if(m.matches()){
                return true;
            }else{
                Alert alert = AlertFactory.getInstance().generateAlert(Alert.AlertType.INFORMATION, INFORMATION_TITLE, "Invalid PIVA", "Please enter a valid PIVA.");
                alert.showAndWait();
                return false;
            }

        }
    }

    public static boolean isSomethingNull(List<String> listToCheck) {
        if(!listToCheck.isEmpty()){
            for (String s : listToCheck) {
                if (Objects.equals(s, "")) {
                    Alert alert = AlertFactory.getInstance().generateAlert(Alert.AlertType.INFORMATION, INFORMATION_TITLE, "Some fields are empty!", "Please fill out all fields.");
                    alert.showAndWait();
                    return true;
                }
            }
            return false;
        }else{
            return true;
        }
    }

    public static boolean checkAddress(String text){
        StringTokenizer st = new StringTokenizer(text, "-");
        if(st.countTokens() == 3){
            String[] address = text.split("-");
            return isInteger(address[2], "Information", "CAP must be a number.", "Please follow the syntax Street-City-CAP");
        } else {
            Alert alert = AlertFactory.getInstance().generateAlert(Alert.AlertType.INFORMATION, INFORMATION_TITLE, "Address field is not correct", "Please follow the syntax Street-City-CAP.");
            alert.showAndWait();
            return false;
        }
    }
}
