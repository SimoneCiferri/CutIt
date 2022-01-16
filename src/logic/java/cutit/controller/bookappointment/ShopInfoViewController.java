package cutit.controller.bookappointment;

import cutit.bean.CustomerBean;
import cutit.bean.firstui.ShopBeanUQ;
import cutit.decorator.ViewLayout;
import cutit.decorator.concreteDecorator.ClientBookAppointmentView;
import cutit.facade.Facade;
import cutit.factory.JavaFXNodeFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShopInfoViewController {

    private ShopBeanUQ shopBeanUQ;
    private CustomerBean customerBeanFirstUI;
    private BookAppointmentController bookAppointmentController;
    private List<ImageView> ivList = new ArrayList<>();

    @FXML
    private Label lShopName, lShopPhone, lShopDescription, lShopOpenTime, lTitleEmployee, lEmployee, lblPhoto;

    @FXML
    private VBox vboxShopInfo, vboxPhoto;

    @FXML
    private ImageView ivShopProfPhoto, ivShop1, ivShop2, ivShop3, ivShop4, ivShop5, ivShop6, ivShop7, ivShop8;


    public boolean initialize() throws IOException {
        shopBeanUQ = new ShopBeanUQ();
        bookAppointmentController = new BookAppointmentController();
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
        lShopName.setText(shopBeanUQ.getShopName());
        if(Objects.equals(shopBeanUQ.getPhoneNumber(), "")){
            lShopPhone.setVisible(true);
            lShopPhone.setText(shopBeanUQ.getPhoneNumber());
        } else {
            lShopPhone.setVisible(false);
        }
        if(!shopBeanUQ.getImages().isEmpty()){
            ivShopProfPhoto.setVisible(true);
            lblPhoto.setVisible(true);
            vboxPhoto.setVisible(true);
            for(int i = 0; i< shopBeanUQ.getImages().size(); i++){
                if(i==0){
                    ivShopProfPhoto.setImage(new Image(String.valueOf(shopBeanUQ.getImages().get(i).toURI())));
                }
                ivList.get(i).setImage(new Image(String.valueOf(shopBeanUQ.getImages().get(i).toURI())));
            }
        } else {
            ivShopProfPhoto.setVisible(false);
            lblPhoto.setVisible(false);
            vboxPhoto.setVisible(false);
        }
        if(Objects.equals(shopBeanUQ.getShopDescription(), "")){
            lShopDescription.setVisible(true);
            lShopDescription.setText(shopBeanUQ.getShopDescription());
        } else {
            lShopDescription.setVisible(false);
        }
        lShopOpenTime.setText(shopBeanUQ.getOpenTime() + " - " + shopBeanUQ.getCloseTime());
        //priceList
        if(Objects.equals(shopBeanUQ.getEmployee(), "")){
            lTitleEmployee.setVisible(true);
            lEmployee.setVisible(true);
            lEmployee.setText(shopBeanUQ.getEmployee());
        } else{
            lTitleEmployee.setVisible(false);
            lEmployee.setVisible(false);
        }
    }

    @FXML
    public boolean bookAppointment() {
        Facade.getInstance().decorateView(ViewLayout.CLIENTBOOKAPPOINTMENT);
        ClientBookAppointmentView view = (ClientBookAppointmentView) Facade.getInstance().getViewMap().get(ViewLayout.CLIENTBOOKAPPOINTMENT);
        CustomerBookAppointmentViewController viewController = (CustomerBookAppointmentViewController) view.getLoadedViewController(ViewLayout.CLIENTBOOKAPPOINTMENT);
        viewController.fillView(customerBeanFirstUI, shopBeanUQ);
        return true;
    }

    @FXML
    public boolean backToHome(){
        Facade.getInstance().decorateView(ViewLayout.HOME);
        return true;
    }

    @FXML
    public boolean goToDirections(){
        Facade.getInstance().decorateView(ViewLayout.GMAPS);
        return true;
    }

    public void fillView(CustomerBean customerBeanFirstUI, String shopName){
        try {
            this.customerBeanFirstUI = customerBeanFirstUI;
            bookAppointmentController.getShop(shopBeanUQ, shopName);
            showShopInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
