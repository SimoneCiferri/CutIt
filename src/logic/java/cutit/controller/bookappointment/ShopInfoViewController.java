package cutit.controller.bookappointment;

import cutit.bean.CustomerBean;
import cutit.bean.ShopBean;
import cutit.controller.getlocationdirections.GetLocationDirectionsController;
import cutit.controller.getlocationdirections.GetLocationDirectionsGoogleMapsViewController1;
import cutit.decorator.ViewLayout1;
import cutit.decorator.concrete_decorator.CustomerBookAppointmentView1;
import cutit.decorator.concrete_decorator.GetLocationDirectionsGoogleMapsView1;
import cutit.exception.DBConnectionException;
import cutit.exception.RecordNotFoundException;
import cutit.facade.Facade;
import cutit.factory.AlertFactory;
import cutit.utils.MyStringBuilder;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ShopInfoViewController {

    private ShopBean shopBean;
    private CustomerBean customerBeanFirstUI;
    private BookAppointmentController bookAppointmentController;
    private GetLocationDirectionsController getLocationDirectionsController;
    private List<ImageView> ivList = new ArrayList<>();
    private static final String CONNECTION_ERROR_TITLE = "Connection error";
    private static final String WARNING_TITLE = "Warning";
    private static final String IO_ERROR_TITLE = "Error";
    private static final String CONNECTION_ERROR_MESSAGE = "Please check your internet connection. If problem persists try to restart the application.";
    private static final String SQL_ERROR_MESSAGE = "Please check your internet connection. If problem persists contact us at cutitapp@support.com.";
    private static final String IO_ERROR_MESSAGE = "Impossible to load some files. If problem persists try again later or contact us at cutitapp@support.com";

    @FXML
    private Button btnMaps;

    @FXML
    private Label lShopName;

    @FXML
    private Label lShopPhone;

    @FXML
    private Label lShopDescription;

    @FXML
    private Label lShopOpenTime;

    @FXML
    private Label lblServiceList;

    @FXML
    private Label lTitleEmployee;

    @FXML
    private Label lEmployee;

    @FXML
    private Label lblPhoto;

    @FXML
    private VBox vboxPhoto;

    @FXML
    private ImageView ivShopProfPhoto;

    @FXML
    private ImageView ivShop1;

    @FXML
    private ImageView ivShop2;

    @FXML
    private ImageView ivShop3;

    @FXML
    private ImageView ivShop4;

    @FXML
    private ImageView ivShop5;

    @FXML
    private ImageView ivShop6;

    @FXML
    private ImageView ivShop7;

    @FXML
    private ImageView ivShop8;

    public boolean initialize() throws IOException {
        shopBean = new ShopBean();
        bookAppointmentController = new BookAppointmentController();
        getLocationDirectionsController = new GetLocationDirectionsController();
        ivList = Arrays.asList(ivShop1, ivShop2, ivShop3, ivShop4, ivShop5, ivShop6, ivShop7, ivShop8);
        return true;
    }

    public void showShopInfo(){
        lShopName.setText(shopBean.getShopName());
        if(!Objects.equals(shopBean.getShopPhoneNumber(), "")){
            lShopPhone.setVisible(true);
            lShopPhone.setText(shopBean.getShopPhoneNumber());
        }lShopPhone.setVisible(false);

        if(!shopBean.getImages().isEmpty()){
            ivShopProfPhoto.setVisible(true);
            lblPhoto.setVisible(true);
            vboxPhoto.setVisible(true);
            for(int i = 0; i< shopBean.getImages().size(); i++){
                if(i==0){
                    ivShopProfPhoto.setImage(new Image(String.valueOf(shopBean.getImages().get(i).toURI())));
                }
                ivList.get(i).setImage(new Image(String.valueOf(shopBean.getImages().get(i).toURI())));
            }
        } else {
            lblPhoto.setVisible(false);
            vboxPhoto.setVisible(false);
        }
        if(Objects.equals(shopBean.getShopAddress(), "")){
            btnMaps.setDisable(true);
        }
        if(!Objects.equals(shopBean.getShopDescription(), "")){
            lShopDescription.setVisible(true);
            lShopDescription.setText(shopBean.getShopDescription());
        }
        Map<Integer,Boolean> opendays = shopBean.getShopOpenDays();
        String openTimes = MyStringBuilder.getStringFromOpenDaysMap(opendays);
        if(!(openTimes.equals(""))){
            openTimes =  openTimes + (shopBean.getShopOpenTime() + " - " + shopBean.getShopCloseTime());
            lShopOpenTime.setText(openTimes);
        } else {
            lShopOpenTime.setText("Shop is closed.");
        }
        if(!shopBean.getServices().isEmpty()){
            List<String> allServices = shopBean.getServices();
            String servicesLabel = MyStringBuilder.getStringFromServicesStringList(allServices);
            lblServiceList.setText(servicesLabel);
        }
        if(!Objects.equals(shopBean.getShopEmployee(), "")){
            lTitleEmployee.setVisible(true);
            lEmployee.setVisible(true);
            lEmployee.setText(shopBean.getShopEmployee());
        }else {
            lTitleEmployee.setVisible(false);
            lEmployee.setVisible(false);
        }
    }


    @FXML
    public boolean bookAppointment() {
        Facade.getInstance().decorateView(ViewLayout1.CUSTOMERBOOKAPPOINTMENT);
        CustomerBookAppointmentView1 view = (CustomerBookAppointmentView1) Facade.getInstance().getViewMap().get(ViewLayout1.CUSTOMERBOOKAPPOINTMENT);
        CustomerBookAppointmentViewController viewController = (CustomerBookAppointmentViewController) view.getLoadedViewController1(ViewLayout1.CUSTOMERBOOKAPPOINTMENT);
        viewController.fillView(customerBeanFirstUI, shopBean);
        return true;
    }

    @FXML
    public boolean backToHome(){
        Facade.getInstance().decorateView(ViewLayout1.HOME);
        return true;
    }

    @FXML
    public boolean goToDirections(){
        Facade.getInstance().decorateView(ViewLayout1.GMAPS);
        GetLocationDirectionsGoogleMapsView1 view = (GetLocationDirectionsGoogleMapsView1) Facade.getInstance().getViewMap().get(ViewLayout1.GMAPS);
        GetLocationDirectionsGoogleMapsViewController1 viewController = (GetLocationDirectionsGoogleMapsViewController1) view.getLoadedViewController1(ViewLayout1.GMAPS);
        getLocationDirectionsController.getDirection(viewController , shopBean);
        return true;
    }

    public void fillView(CustomerBean customerBeanFirstUI, String shopName){
        try {
            this.customerBeanFirstUI = customerBeanFirstUI;
            bookAppointmentController.getShop(shopBean, shopName);
            showShopInfo();
        } catch (RecordNotFoundException e) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.WARNING, WARNING_TITLE, e.getMessage());
            alert.showAndWait();
        } catch(DBConnectionException dbe){
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, CONNECTION_ERROR_TITLE, CONNECTION_ERROR_MESSAGE);
            alert.showAndWait();
        } catch (SQLException sqle) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, CONNECTION_ERROR_TITLE, SQL_ERROR_MESSAGE);
            alert.showAndWait();
        } catch (IOException ioe) {
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.ERROR, IO_ERROR_TITLE, IO_ERROR_MESSAGE);
            alert.showAndWait();
        }
    }

}
