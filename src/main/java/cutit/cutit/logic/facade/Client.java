package cutit.cutit.logic.facade;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Client extends Application {

    @Override
    public void start(Stage stage) {
        Facade facade = Facade.getInstance();
        Group root = new Group();
        root.getChildren().add(facade.getSTartView().getPrLayout());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Cut-It!");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}