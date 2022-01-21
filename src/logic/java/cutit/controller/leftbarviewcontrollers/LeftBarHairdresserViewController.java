package cutit.controller.leftbarviewcontrollers;

import cutit.bean.CustomerBean;
import cutit.bean.HairdresserBean;
import cutit.bean.ShopBean;
import cutit.controller.deletebookedappointments.HairdresserDeleteBookedAppointmentsViewController;
import cutit.controller.managepromotions.HairdresserManagePromotionsViewController;
import cutit.controller.manageservices.HairdresserManageServicesViewController;
import cutit.controller.manageshoppage.HairdresserManageShopPageViewController;
import cutit.decorator.ViewLayout1;
import cutit.decorator.ViewLayout2;
import cutit.decorator.concrete_decorator.HairdresserAppointmentsView1;
import cutit.decorator.concrete_decorator.HairdresserManageShopView1;
import cutit.decorator.concrete_decorator.HairdresserPromotionsView1;
import cutit.decorator.concrete_decorator.HairdresserServicesView1;
import cutit.facade.Facade;
import cutit.facade.Facade2;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LeftBarHairdresserViewController {

    private double xOffset = 0;
    private double yOffset = 0;
    private static final double onPageStyle = 60;
    private static final double normalStyle = 40;

    private HairdresserBean hairdresserBeanSecondUI;
    private ShopBean shopBeanSecondUI;

    @FXML
    private VBox vbLeftBarHairdresser;

    @FXML
    private ImageView ivShopAppointments, ivShopPromotions, ivShopServices, ivShopPage, ivLogout;

    @FXML
    public boolean shopAppointmentsPage() {
        setOnPageStyle(ivShopAppointments);
        Facade2.getInstance().decorateView2(ViewLayout2.HAIRDRESSERDELETEBOOKEDAPPOINTMENTS);
        /*deleteAppointmentBeanFirstUI.setShopName(shopBeanFirstUI.getShopName());
        deleteAppointmentBeanFirstUI.setAllAppointments(shopBeanFirstUI.getAllAppointments());
        HairdresserAppointmentsView1 view = (HairdresserAppointmentsView1) Facade.getInstance().getViewMap().get(ViewLayout1.HAIRDRESSERAPPOINTMENTS);
        HairdresserDeleteBookedAppointmentsViewController viewController = (HairdresserDeleteBookedAppointmentsViewController) view.getLoadedViewController1(ViewLayout1.HAIRDRESSERAPPOINTMENTS);
        viewController.fillView(shopBeanFirstUI);*/
        return true;
    }

    @FXML
    public boolean shopPromotionPage() {
        setOnPageStyle(ivShopPromotions);
        Facade2.getInstance().decorateView2(ViewLayout2.HAIRDRESSERMANAGEPROMOTIONS);

        /*HairdresserPromotionsView1 view = (HairdresserPromotionsView1) Facade.getInstance().getViewMap().get(ViewLayout1.HAIRDRESSERPROMOTIONS);
        HairdresserManagePromotionsViewController viewController = (HairdresserManagePromotionsViewController) view.getLoadedViewController1(ViewLayout1.HAIRDRESSERPROMOTIONS);
        viewController.fillView(shopBeanFirstUI);*/
        return true;
    }

    @FXML
    public boolean servicesPage() {
        setOnPageStyle(ivShopServices);
        Facade2.getInstance().decorateView2(ViewLayout2.HAIRDRESSERMANAGESERVICES);

        /*HairdresserServicesView1 view = (HairdresserServicesView1)  Facade.getInstance().getViewMap().get(ViewLayout1.HAIRDRESSERSERVICES);
        HairdresserManageServicesViewController viewController = (HairdresserManageServicesViewController) view.getLoadedViewController1(ViewLayout1.HAIRDRESSERSERVICES);
        viewController.fillView(shopBeanFirstUI);*/
        return true;
    }

    @FXML
    public boolean shopPage() {
        setOnPageStyle(ivShopPage);
        Facade2.getInstance().decorateView2(ViewLayout2.HAIRDRESSERMANAGESHOPPAGE);

        /*HairdresserManageShopView1 view = (HairdresserManageShopView1) Facade.getInstance().getViewMap().get(ViewLayout1.HAIRDRESSERMANAGESHOP);
        HairdresserManageShopPageViewController viewController = (HairdresserManageShopPageViewController) view.getLoadedViewController1(ViewLayout1.HAIRDRESSERMANAGESHOP);
        viewController.fillView(shopBeanFirstUI);*/
        return true;
    }

    @FXML
    public boolean logout() {
        setOnPageStyle(ivLogout);
        Facade2.getInstance().getStartView2().getPrLayout2().getChildren().remove(vbLeftBarHairdresser);
        Facade2.getInstance().logout();
        Facade2.getInstance().decorateView2(ViewLayout2.LEFTBAR);
        Facade2.getInstance().decorateView2(ViewLayout2.LOGIN2);
        return true;
    }

    @FXML
    public void initialize(){
        System.out.println("LeftBarHairdresser viewcontroller");
    }

    public void startBean(HairdresserBean hairdresserBeanSecondUI, ShopBean shopBeanSecondUI){
        System.out.println("HairdresserBeanSecondUI passedBY LoginViewController2");
        this.hairdresserBeanSecondUI = hairdresserBeanSecondUI;
        this.shopBeanSecondUI = shopBeanSecondUI;
        shopAppointmentsPage();
    }


    private void setOnPageStyle(ImageView imageViewOnPage){
        ivShopAppointments.setFitWidth(normalStyle);
        ivShopAppointments.setFitHeight(normalStyle);
        ivShopPromotions.setFitWidth(normalStyle);
        ivShopPromotions.setFitHeight(normalStyle);
        ivShopServices.setFitWidth(normalStyle);
        ivShopServices.setFitHeight(normalStyle);
        ivShopPage.setFitWidth(normalStyle);
        ivShopPage.setFitHeight(normalStyle);
        ivLogout.setFitWidth(normalStyle);
        ivLogout.setFitHeight(normalStyle);

        imageViewOnPage.setFitWidth(onPageStyle);
        imageViewOnPage.setFitHeight(onPageStyle);
    }

    @FXML
    public void closeIV(){
        Stage stage = (Stage)vbLeftBarHairdresser.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void reduce(){
        Stage stage = (Stage)vbLeftBarHairdresser.getScene().getWindow();
        stage.setIconified(true);
    }


    @FXML
    void getOffset(MouseEvent event) {
        xOffset = vbLeftBarHairdresser.getScene().getWindow().getX() - event.getScreenX();
        yOffset = vbLeftBarHairdresser.getScene().getWindow().getY() - event.getScreenY();
    }

    @FXML
    void setOff(MouseEvent event) {
        vbLeftBarHairdresser.getScene().getWindow().setX(event.getScreenX() + xOffset);
        vbLeftBarHairdresser.getScene().getWindow().setY(event.getScreenY() + yOffset);
    }
}
