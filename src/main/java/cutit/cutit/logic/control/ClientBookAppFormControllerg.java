package cutit.cutit.logic.control;

import cutit.cutit.logic.views.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientBookAppFormControllerg {

    private final Stage prStage = Client.getPrStage();
    private BorderPane pLayout = null;
    private BorderPane nLayout = null;
    private final String transparentStyle = "-fx-background-color: transparent; ";
    private final String pageFlagStyle = "-fx-background-color: #707070; -fx-text-fill: #FFFFFF; ";

    @FXML
    private Label labelDateTime;

    @FXML
    public boolean bookAppPay() throws IOException {
        System.out.println("Book Button pressed (Book App Form)");
        /*
        VBox bookAppFormLayout = null;
        bookAppFormLayout = FXMLLoader.load(MainView.class.getResource("/cutit/cutit/views/clientbookappform.fxml"));
        nLayout= MainView.getNdLayout();
        nLayout.setCenter(bookAppFormLayout);
         */
        return true;
    }

    @FXML
    public boolean backToBookApp() throws IOException {
        System.out.println("Back Button pressed (Book App Form)");
        BorderPane bookAppLayout = null;
        bookAppLayout = FXMLLoader.load(Client.class.getResource("/cutit/cutit/views/clientbookappointment.fxml"));
        Image image = new Image(Client.class.getResource(Client.getBackgr()).toString());
        BackgroundImage back = new BackgroundImage(image, null, null, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        bookAppLayout.setBackground(new Background(back));
        pLayout= Client.getPrLayout();
        pLayout.setCenter(bookAppLayout);
        return true;
    }


    public boolean initialize(){
        labelDateTime.setText(ClientBookAppointmentViewController.getAppDate());
        return true;
    }

}
