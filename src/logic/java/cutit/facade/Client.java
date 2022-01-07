package cutit.facade;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Client extends Application {

    @Override
    public void start(Stage stage) {
        Facade facade = Facade.getInstance();
        Group root = new Group();
        root.getChildren().add(facade.getStartView().getPrLayout());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Cut-It!");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}