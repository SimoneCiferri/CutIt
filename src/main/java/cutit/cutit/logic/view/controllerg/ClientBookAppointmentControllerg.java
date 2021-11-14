package cutit.cutit.logic.view.controllerg;

import cutit.cutit.logic.view.MainView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientBookAppointmentControllerg {

    private final Stage prStage = MainView.getPrStage();
    private BorderPane pLayout = null;
    private BorderPane nLayout = null;
    private final String transparentStyle = "-fx-background-color: transparent; ";
    private final String pageFlagStyle = "-fx-background-color: #707070; -fx-text-fill: #FFFFFF; ";

    @FXML
    private BorderPane bpInBookApp;

    public boolean initialize() throws IOException {
        MainView.setNdLayout(bpInBookApp);
        System.out.println("Book App page");
        return true;
    }

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
        nLayout = MainView.getNdLayout();
        nLayout.setCenter(photoLayout);
        pLayout= MainView.getPrLayout();
        pLayout.setCenter(shopLayout);
        return true;
    }

}
