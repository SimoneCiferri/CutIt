package cutit.controller.leftbarviewcontrollers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LeftBarCustomerViewController {

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private VBox vbLeftBarCustomer;

    @FXML
    public void initialize(){
        System.out.println("LeftBarCustomer viewcontroller");
    }

    @FXML
    public void closeIV(){
        Stage stage = (Stage)vbLeftBarCustomer.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void reduce(){
        Stage stage = (Stage)vbLeftBarCustomer.getScene().getWindow();
        stage.setIconified(true);
    }


    @FXML
    void getOffset(MouseEvent event) {
        xOffset = vbLeftBarCustomer.getScene().getWindow().getX() - event.getScreenX();
        yOffset = vbLeftBarCustomer.getScene().getWindow().getY() - event.getScreenY();
    }

    @FXML
    void setOff(MouseEvent event) {
        vbLeftBarCustomer.getScene().getWindow().setX(event.getScreenX() + xOffset);
        vbLeftBarCustomer.getScene().getWindow().setY(event.getScreenY() + yOffset);
    }
}
