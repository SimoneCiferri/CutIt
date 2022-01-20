package cutit.utils;

import cutit.factory.AlertFactory;
import javafx.scene.control.Alert;

import java.util.List;
import java.util.Objects;
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
            AlertFactory.getInstance().generateAlert(Alert.AlertType.INFORMATION, title, headerText, contentText);
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
            AlertFactory.getInstance().generateAlert(Alert.AlertType.INFORMATION, title, headerText, contentText);
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
                AlertFactory.getInstance().generateAlert(Alert.AlertType.INFORMATION, title, headerText, contentText);
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
                AlertFactory.getInstance().generateAlert(Alert.AlertType.INFORMATION, INFORMATION_TITLE, "Invalid Email", "Please enter a valid email address.");
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
                AlertFactory.getInstance().generateAlert(Alert.AlertType.INFORMATION, INFORMATION_TITLE, "Invalid PIVA", "Please enter a valid PIVA.");
                return false;
            }

        }
    }

    public static boolean isSomethingNull(List<String> listToCheck) {
        if(!listToCheck.isEmpty()){
            for (String s : listToCheck) {
                if (Objects.equals(s, "")) {
                    AlertFactory.getInstance().generateAlert(Alert.AlertType.INFORMATION, INFORMATION_TITLE, "Some fields are empty!", "Please fill out all fields.");
                    return true;
                }
            }
            return false;
        }else{
            return true;
        }
    }
}
