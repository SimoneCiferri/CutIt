package cutit.cutit.logic.view.controllerg;

import com.jfoenix.controls.JFXDatePicker;
import cutit.cutit.logic.view.MainView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ClientBookAppointmentControllerg implements Initializable{

    private final Stage prStage = MainView.getPrStage();
    private BorderPane pLayout = null;
    private BorderPane nLayout = null;
    private final String transparentStyle = "-fx-background-color: transparent; ";
    private final String pageFlagStyle = "-fx-background-color: #707070; -fx-text-fill: #FFFFFF; ";

    @FXML
    private BorderPane bpInBookApp;

    @FXML
    private Button btnTest;

    @FXML
    private Label labelDate;

    @FXML
    private DatePicker dtPicker;

    @FXML
    public boolean bookAppNext() throws IOException {
        System.out.println("Next Button pressed (Book App)");
        VBox bookAppFormLayout = null;
        bookAppFormLayout = FXMLLoader.load(MainView.class.getResource("/cutit/cutit/views/clientbookappform.fxml"));
        nLayout= MainView.getNdLayout();
        nLayout.setCenter(bookAppFormLayout);
        return true;
    }

    @FXML
    public boolean backToShopInfo() throws IOException {
        System.out.println("Back Button pressed (Book App)");
        VBox shopLayout = null;
        shopLayout = FXMLLoader.load(MainView.class.getResource("/cutit/cutit/views/clientshopinfo.fxml"));
        VBox photoLayout = null;
        photoLayout = FXMLLoader.load(MainView.class.getResource("/cutit/cutit/views/clientshopphoto.fxml"));
        Image image = new Image(MainView.class.getResource(MainView.getBackgr()).toString());
        BackgroundImage back = new BackgroundImage(image, null, null, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        shopLayout.setBackground(new Background(back));
        nLayout = MainView.getNdLayout();
        nLayout.setCenter(photoLayout);
        pLayout= MainView.getPrLayout();
        pLayout.setCenter(shopLayout);
        return true;
    }

    @FXML
    private void onDate(){
        LocalDate lDate = dtPicker.getValue();
        labelDate.setText(lDate.toString());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MainView.setNdLayout(bpInBookApp);
        System.out.println("Book App page");
    }
}
