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
    private VBox VBoxPayPal;


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
            ImageView imageView = new ImageView();
            //Setting image to the image view
            imageView.setImage(image);
            //Setting the image view parameters
            imageView.setX(10);
            imageView.setY(10);
            imageView.setFitWidth(575);
            imageView.setPreserveRatio(true);
            //Setting the Scene object
            Group root = new Group(imageView);
            Scene scene = new Scene(root, 595, 370);
            /*stage.setTitle("PayPal Payment confirmed");
            stage.setScene(scene);
            stage.show();
             */
        }
        String price = payOnlineBean.getServicePrice();
        String name = payOnlineBean.getPaymentShopName();

        //quì riempirò i campi delle TextFile/TextArea/Label dell'fxml grazie ai getter della bean che mi è stata passata in ingresso
        //showClientApp();
    }
