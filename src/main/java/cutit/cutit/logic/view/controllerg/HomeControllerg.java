package cutit.cutit.logic.view.controllerg;

import cutit.cutit.logic.view.MainView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.IOException;
import com.jfoenix.controls.JFXButton;

public class HomeControllerg {

    private final Stage prStage = MainView.getPrStage();
    private BorderPane pLayout = null;
    private BorderPane nLayout = null;
    private Integer currPage = 1;
    private final String transparentStyle = "-fx-background-color: transparent; ";
    private final String pageFlagStyle = "-fx-background-color: #707070; -fx-text-fill: #FFFFFF; ";
    private final String labelStyle = "-fx-border-color: grey; -fx-border-radius: 5; -fx-text-fill: #FFFFFF;";

    @FXML
    private VBox vbInScroll;

    @FXML
    private JFXButton btnSearch;

    public boolean initialize() throws IOException {
        Image image = new Image(getClass().getResource(MainView.getBackgr()).toString());
        BackgroundImage back = new BackgroundImage(image, null, null, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        vbInScroll.setBackground(new Background(back));
        vbInScroll.setSpacing(15);
        showShops();
        return true;
    }

    private void showShops(){
        for(Integer i=0; i<6; i++){
            Label l = new Label("Barber"+i.toString());
            l.setPrefSize(895,130);
            l.setMinSize(895,130);
            l.setMaxSize(895,130);
            l.setStyle(labelStyle);
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
        HBox key = new HBox();
        key.setPrefSize(100, 50);
        key.setMaxSize(100, 50);
        key.setMinSize(100, 50);
        key.setSpacing(30);
        Label prev = new Label("<<");
        prev.setDisable(true);
        prev.setOnMouseClicked((MouseEvent) -> {
            currPage--;
            if(currPage <= 1){
                prev.setDisable(true);
            }else{
                prev.setDisable(false);
            }
        });
        Label next = new Label(">>");
        next.setOnMouseClicked((MouseEvent) -> {
            currPage++;
            if(currPage <= 1){
                prev.setDisable(true);
            }else{
                prev.setDisable(false);
            }
        });
        key.getChildren().addAll(prev,next);
        vbInScroll.getChildren().add(key);
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
