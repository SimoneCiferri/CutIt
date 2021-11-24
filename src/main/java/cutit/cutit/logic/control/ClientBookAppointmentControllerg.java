package cutit.cutit.logic.control;

import cutit.cutit.logic.views.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class ClientBookAppointmentControllerg{

    private final Stage prStage = Client.getPrStage();
    private BorderPane pLayout = null;
    private BorderPane nLayout = null;
    private final String transparentStyle = "-fx-background-color: transparent; ";
    private final String pageFlagStyle = "-fx-background-color: #707070; -fx-text-fill: #FFFFFF; ";
    private static String startAppointment = "";

    @FXML
    private BorderPane bpInBookApp;

    @FXML
    private Label labelDate, label830;

    @FXML
    private DatePicker dtPicker;

    @FXML
    public boolean bookAppNext() throws IOException {
        System.out.println("Next Button pressed (Book App)");
        VBox bookAppFormLayout = null;
        FXMLLoader loader = new FXMLLoader(Client.class.getResource("/cutit/cutit/views/clientbookappform.fxml"));
        bookAppFormLayout = loader.load();
        nLayout= Client.getNdLayout();
        nLayout.setCenter(bookAppFormLayout);
        return true;
    }

    @FXML
    public boolean backToShopInfo() throws IOException {
        System.out.println("Back Button pressed (Book App)");
        VBox shopLayout = null;
        shopLayout = FXMLLoader.load(Client.class.getResource("/cutit/cutit/views/shopinfo.fxml"));
        VBox photoLayout = null;
        photoLayout = FXMLLoader.load(Client.class.getResource("/cutit/cutit/views/clientshopphoto.fxml"));
        Image image = new Image(Client.class.getResource(Client.getBackgr()).toString());
        BackgroundImage back = new BackgroundImage(image, null, null, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        shopLayout.setBackground(new Background(back));
        nLayout = Client.getNdLayout();
        nLayout.setCenter(photoLayout);
        pLayout= Client.getPrLayout();
        pLayout.setCenter(shopLayout);
        return true;
    }

    @FXML
    private void set830(){
        labelDate.setText(dtPicker.getValue().toString() + "T" + "08:30:00");
        startAppointment = dtPicker.getValue().toString() + "T" + "08:30:00";
        label830.setBorder(new Border(new BorderStroke(Color.GREY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(5))));
    }

    public boolean initialize(){
        Client.setNdLayout(bpInBookApp);
        System.out.println("Book App page");
        dtPicker.setValue(LocalDate.now());
        return true;
    }

    public static String getAppDate(){
        return startAppointment;
    }

}
