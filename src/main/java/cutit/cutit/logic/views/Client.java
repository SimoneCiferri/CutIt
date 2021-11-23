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

    private static Stage prStage;
    private static BorderPane prLayout;
    private static BorderPane ndLayout;

    @Override
    public void start(Stage stage) throws IOException {
        Facade facade = Facade.getInstance();
        Group root = new Group();
        root.getChildren().add(facade.getSTartView().getPrLayout());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        facade.decorateView(ViewLayout.HOME);
        stage.show();
        /*
        startPrStage(stage);
        startPrLayout();
        startPrScene();
        stage.show();

         */
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
        setPrLayout(FXMLLoader.load(Client.class.getResource("/cutit/cutit/views/start.fxml")));
        VBox homeLayout = null;
        homeLayout = FXMLLoader.load(Client.class.getResource("/cutit/cutit/views/home.fxml"));
        Image image = new Image(Client.class.getResource(Client.getBackgr()).toString());
        BackgroundImage back = new BackgroundImage(image, null, null, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        homeLayout.setBackground(new Background(back));
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

    public static void setNdLayout(BorderPane ndL){
        ndLayout = ndL;
    }

    public static BorderPane getNdLayout(){
        return ndLayout;
    }

    public static String getBackgr(){
        return "/cutit/cutit/files/backgr.jpg";
    }

    public static void main(String[] args) {
        launch();
    }
}