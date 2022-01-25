package cutit.controller.bookappointment;

import cutit.bean.CustomerBean;
import cutit.bean.ShopBean;
import cutit.controller.getlocationdirections.GetLocationDirectionsController;
import cutit.controller.getlocationdirections.GetLocationDirectionsGoogleMapsViewController1;
import cutit.decorator.ViewLayout1;
import cutit.decorator.concrete_decorator.CustomerBookAppointmentView1;
import cutit.decorator.concrete_decorator.GetLocationDirectionsGoogleMapsView1;
import cutit.facade.Facade;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ShopInfoViewController {

    private ShopBean shopBean;
    private CustomerBean customerBeanFirstUI;
    private BookAppointmentController bookAppointmentController;
    private GetLocationDirectionsController getLocationDirectionsController;
    private List<ImageView> ivList = new ArrayList<>();

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
    private VBox vboxShopInfo;

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
        ivList.add(ivShop1);
        ivList.add(ivShop2);
        ivList.add(ivShop3);
        ivList.add(ivShop4);
        ivList.add(ivShop5);
        ivList.add(ivShop6);
        ivList.add(ivShop7);
        ivList.add(ivShop8);
        return true;
    }

    public void showShopInfo(){
        lShopName.setText(shopBean.getShopName());
        if(Objects.equals(shopBean.getShopPhoneNumber(), "")){
            lShopPhone.setVisible(true);
            lShopPhone.setText(shopBean.getShopPhoneNumber());
        }
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
        StringBuilder openTimes = new StringBuilder();
        for(int i=1;i<opendays.size()+1;i++){
           if(opendays.get(i)){
               switch (i){
                   case 1:{
                       openTimes.append("Monday").append("\n");
                       break;
                   }case 2:{
                       openTimes.append("Tuesday").append("\n");
                       break;
                   }case 3:{
                       openTimes.append("Wednesday").append("\n");
                       break;
                   }case 4:{
                       openTimes.append("Thursday").append("\n");
                       break;
                   }case 5:{
                       openTimes.append("Friday").append("\n");
                       break;
                   }case 6:{
                       openTimes.append("Saturday").append("\n");
                       break;
                   }case 7:{
                       openTimes.append("Sunday").append("\n");
                       break;
                   } default:{}
               }
           }
        }
        if(!(openTimes.toString().equals(""))){
            openTimes.append(shopBean.getShopOpenTime()).append(" - ").append(shopBean.getShopCloseTime());
            lShopOpenTime.setText(openTimes.toString());
        } else {
            lShopOpenTime.setText("Shop is closed.");
        }
        if(!shopBean.getServices().isEmpty()){
            List<String> allServices = shopBean.getServices();
            StringBuilder servicesLabel = new StringBuilder();
            for (String allService : allServices) {
                servicesLabel.append(allService).append("\n");
            }
            lblServiceList.setText(servicesLabel.toString());
        }
        if(!Objects.equals(shopBean.getShopEmployee(), "")){
            lTitleEmployee.setVisible(true);
            lEmployee.setVisible(true);
            lEmployee.setText(shopBean.getShopEmployee());
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
