package cutit.cutit.logic.factory;

import cutit.cutit.logic.facade.Facade;
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


    public void generateAlert(String title, String headerText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    public void generateAlert(Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setTitle("Error!");
        alert.setHeaderText("An error occured!");
        alert.setContentText("Try to restart the application. If the problem persist try reinstalling the application.");
        alert.showAndWait();
        if(type == Alert.AlertType.ERROR){
            Stage stage = (Stage) Facade.getInstance().getStartView().getPrLayout().getScene().getWindow();
            stage.close();
        }
    }

}
