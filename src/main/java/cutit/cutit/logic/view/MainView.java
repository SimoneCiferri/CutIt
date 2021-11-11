package cutit.cutit.logic.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class MainView extends Application {

    private static Stage prStage;
    private static BorderPane prLayout;

    @Override
    public void start(Stage stage) throws IOException {
        startPrStage(stage);
        startPrLayout();
        startPrScene();
        stage.show();
    }

    private static void startPrStage(Stage stage){
        setPrStage(stage);
        prStage.setTitle("Cut-It!");
        prStage.setResizable(false);
    }

    public static void setPrStage(Stage pStage){
        prStage = pStage;
    }

    public static Stage getPrStage(){
        return prStage;
    }

    private static boolean startPrLayout() throws IOException{
        setPrLayout(FXMLLoader.load(MainView.class.getResource("/cutit/cutit/views/start.fxml")));
        VBox homeLayout = null;
        homeLayout = FXMLLoader.load(MainView.class.getResource("/cutit/cutit/views/home.fxml"));
        prLayout.setCenter(homeLayout);
        return true;
    }

    public static void setPrLayout(BorderPane prL){
        prLayout = prL;
    }

    public static BorderPane getPrLayout(){
        return prLayout;
    }

    private static void startPrScene(){
        Scene scene = new Scene(prLayout, 1024, 768);
        prStage.setScene(scene);
    }


    public static void main(String[] args) {
        launch();
    }
}