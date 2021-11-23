package cutit.cutit.logic.view.controllerg;

import cutit.cutit.logic.view.MainView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class ClientBookAppFormControllerg {

    private final Stage prStage = MainView.getPrStage();
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
        bookAppLayout = FXMLLoader.load(MainView.class.getResource("/cutit/cutit/views/clientbookappointment.fxml"));
        Image image = new Image(MainView.class.getResource(MainView.getBackgr()).toString());
        BackgroundImage back = new BackgroundImage(image, null, null, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        bookAppLayout.setBackground(new Background(back));
        pLayout= MainView.getPrLayout();
        pLayout.setCenter(bookAppLayout);
        return true;
    }


    public boolean initialize(){
        labelDateTime.setText(ClientBookAppointmentControllerg.getAppDate());
        return true;
    }

}
