package cutit.cutit.logic.exception;

import cutit.cutit.logic.facade.Facade;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class ExceptionHandler {

    private static ExceptionHandler instance = null;

    private ExceptionHandler(){

    }

    public static synchronized ExceptionHandler getInstance(){
        if(ExceptionHandler.instance == null){
            ExceptionHandler.instance = new ExceptionHandler();
        }
        return ExceptionHandler.instance;
    }

    public void handleException(Exception e){
        if(e instanceof IOException){
            generateAlert(Alert.AlertType.ERROR, "IOException", "Io Exception detected!", e.getMessage());
        }
        if(e instanceof NullPointerException){
            generateAlert(Alert.AlertType.ERROR, "NullPointerException", "NullPointerException detected!",e.getMessage());
        }else{
            generateAlert(Alert.AlertType.ERROR, "Exception detected!", e.getMessage(), "Resource files may be deleted or corrupted. If the problem persist try reinstalling the application.");
        }
        Stage stage = (Stage) Facade.getInstance().getSTartView().getPrLayout().getScene().getWindow();
        stage.close();
    }

    private void generateAlert(Alert.AlertType type, String title, String headerText, String contentText){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

}
