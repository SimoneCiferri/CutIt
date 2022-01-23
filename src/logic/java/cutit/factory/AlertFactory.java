package cutit.factory;

import cutit.facade.Facade;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class AlertFactory {

    private static AlertFactory instance = null;

    private AlertFactory(){}

    public static synchronized AlertFactory getInstance(){
        if(AlertFactory.instance == null){
            AlertFactory.instance = new AlertFactory();
        }
        return AlertFactory.instance;
    }

    public Alert generateAlert(Alert.AlertType type, String title, String headerText, String contentText){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        return alert;
    }

    public Alert generateAlert(Alert.AlertType type, String title, String headerText){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        return alert;
    }

}
