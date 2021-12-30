package cutit.cutit.logic.checkTest;

import cutit.cutit.logic.factory.AlertFactory;
import javafx.scene.control.Alert;

public class checkTextField {

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
}
