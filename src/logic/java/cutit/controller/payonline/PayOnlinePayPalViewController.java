package cutit.controller.payonline;

import cutit.bean.CustomerBean;
import cutit.bean.PayOnlineBean;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import cutit.bean.firstui.PayOnlineBeanUQ;
import javafx.fxml.FXML;


public class PayOnlinePayPalViewController {

    private PayOnlineBean payOnlineBean;

    @FXML
    private VBox vbPaypal;

    @FXML
    public void initialize(){
        payOnlineBean = new PayOnlineBeanUQ();
    }

    public void fillView(PayOnlineBean payOnlineBean){
        this.payOnlineBean = payOnlineBean;
        System.out.println("Filling View from PayOnlineBean data passedBY ...");
        start();
    }

    public void start() {
            //creating the image object
        InputStream stream = null;
        try {
            stream = new FileInputStream("src/logic/resources/cutit/cutit/files/paypal confirmation ispw.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image image = new Image(stream);
            //Creating the image view
            ImageView iv = new ImageView();
            //Setting image to the image view
            iv.setImage(image);
            //Setting the image view parameters
            iv.setX(10);
            iv.setY(10);
            iv.setFitWidth(575);
            iv.setPreserveRatio(true);
            //Setting the Scene object
            vbPaypal.getChildren().add(iv);
        }
    }
