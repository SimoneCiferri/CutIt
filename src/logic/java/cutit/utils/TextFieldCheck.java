package cutit.utils;

import cutit.factory.AlertFactory;
import javafx.scene.control.Alert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextFieldCheck {

    public static boolean isNumeric(String strNum, String title, String headerText, String contentText) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
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
            double d = Integer.parseInt(strNum);
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
}
