package cutit.cutit.logic.view.controllerg;

import cutit.cutit.logic.view.MainView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.IOException;

public class HomeControllerg {

    private final Stage prStage = MainView.getPrStage();
    private BorderPane pLayout = null;
    private BorderPane nLayout = null;
    private final String transparentStyle = "-fx-background-color: transparent; ";
    private final String pageFlagStyle = "-fx-background-color: #707070; -fx-text-fill: #FFFFFF; ";
    private Label l;

    @FXML
    private VBox vbInScroll;

    public boolean initialize() throws IOException {
        Image image = new Image(getClass().getResource(MainView.getBackgr()).toString());
        BackgroundImage back = new BackgroundImage(image, null, null, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        vbInScroll.setBackground(new Background(back));
        vbInScroll.setSpacing(15);
        createLabel();
        return true;
    }

    private void createLabel(){
        for(Integer i=0; i<10; i++){
            l = new Label("Barber"+i.toString());
            l.setPrefSize(895,130);
            l.setMinSize(895,130);
            l.setMaxSize(895,130);
            l.setStyle("-fx-border-color: grey; -fx-border-radius: 5;");
            l.setPadding(new Insets(0,0,10,20));
            l.setOnMouseClicked((MouseEvent) -> {
                try {
                    goShopInfo();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            vbInScroll.getChildren().add(l);
        }
    }

    public boolean goShopInfo() throws IOException {
        System.out.println("Shop page (client)");
        VBox shopLayout = null;
        shopLayout = FXMLLoader.load(MainView.class.getResource("/cutit/cutit/views/clientshopinfo.fxml"));
        VBox photoLayout = null;
        photoLayout = FXMLLoader.load(MainView.class.getResource("/cutit/cutit/views/clientshopphoto.fxml"));
        Image image = new Image(getClass().getResource(MainView.getBackgr()).toString());
        BackgroundImage back = new BackgroundImage(image, null, null, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        shopLayout.setBackground(new Background(back));
        nLayout = MainView.getNdLayout();
        nLayout.setCenter(photoLayout);
        pLayout= MainView.getPrLayout();
        pLayout.setCenter(shopLayout);
        return true;
    }

}
