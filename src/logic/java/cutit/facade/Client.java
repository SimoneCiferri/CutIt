package cutit.facade;

import cutit.decorator.decorator2.ViewLayout2;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class Client extends Application {

    @FXML
    private Pane bpSelection;

    @Override
    public void start(Stage stage) {
        FXMLLoader loader = new FXMLLoader(Client.class.getResource("/cutit/cutit/fxml/uiselection.fxml"));
        try {
            Pane primaryLayout = loader.load();
            Image image = new Image(Objects.requireNonNull(ViewLayout2.class.getResource("/cutit/cutit/files/barber-shop-welcome.png"), "Resource files may be deleted or corrupted. If the problem persist try reinstalling the application.").toString());
            BackgroundImage back = new BackgroundImage(image, null, null, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
            primaryLayout.setBackground(new Background(back));
            Group root = new Group();
            root.getChildren().add(primaryLayout);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Cut-It!");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goFirst(){
        Stage stage = (Stage) bpSelection.getScene().getWindow();
        Facade facade = Facade.getInstance();
        Group root = new Group();
        root.getChildren().add(facade.getStartView().getPrLayout1());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void goSecond(){
        Stage stage = (Stage) bpSelection.getScene().getWindow();
        Facade2 facade2 = Facade2.getInstance();
        Group root = new Group();
        root.getChildren().add(facade2.getStartView2().getPrLayout2());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void exitApp(){
        Stage stage = (Stage) bpSelection.getScene().getWindow();
        stage.close();
    }

    public static void main(String[] args) {
        launch();
    }
}