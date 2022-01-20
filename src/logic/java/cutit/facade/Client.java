package cutit.facade;

import cutit.decorator.ViewLayout;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Client extends Application {

    @FXML
    private Pane bpSelection;

    @Override
    public void start(Stage stage) {
        FXMLLoader loader = new FXMLLoader(Client.class.getResource("/cutit/cutit/fxml/uiselection.fxml"));
        try {
            Pane primaryLayout = loader.load();
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
        root.getChildren().add(facade.getStartView().getPrLayout());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void goSecond(){
        Stage stage = (Stage) bpSelection.getScene().getWindow();
        //Facade facade = Facade.getInstance();
        Group root = new Group();
        //root.getChildren().add(facade.getStartView().getPrLayout());
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