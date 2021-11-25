package cutit.cutit.logic.views;

import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.facade.Facade;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class Client extends Application {

    @Override
    public void start(Stage stage) {
        Facade facade = Facade.getInstance();
        Group root = new Group();
        root.getChildren().add(facade.getSTartView().getPrLayout());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        facade.decorateView(ViewLayout.HOME);
        stage.setResizable(false);
        stage.setTitle("Cut-It!");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}