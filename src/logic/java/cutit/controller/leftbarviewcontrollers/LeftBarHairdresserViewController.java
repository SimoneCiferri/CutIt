package cutit.controller.leftbarviewcontrollers;

import cutit.bean.HairdresserBean;
import cutit.bean.ShopBeanInterface;
import cutit.controller.managepromotions.HairdresserManagePromotionsViewController2;
import cutit.controller.manageservices.HairdresserManageServicesViewController2;
import cutit.controller.manageshoppage.HairdresserManageShopPageViewController2;
import cutit.decorator.ViewLayout2;
import cutit.decorator.concrete_decorator2.HairdresserManagePromotionsView2;
import cutit.decorator.concrete_decorator2.HairdresserManageServicesView2;
import cutit.decorator.concrete_decorator2.HairdresserManageShopPageView2;
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
    private ShopBeanInterface shopBeanSecondUI;

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
        HairdresserManagePromotionsView2 view = (HairdresserManagePromotionsView2) Facade2.getInstance().getViewMap().get(ViewLayout2.HAIRDRESSERMANAGEPROMOTIONS);
        HairdresserManagePromotionsViewController2 viewController = (HairdresserManagePromotionsViewController2) view.getLoadedViewController2(ViewLayout2.HAIRDRESSERMANAGEPROMOTIONS);
        viewController.fillView(shopBeanSecondUI);
        return true;
    }

    @FXML
    public boolean servicesPage() {
        setOnPageStyle(ivShopServices);
        Facade2.getInstance().decorateView2(ViewLayout2.HAIRDRESSERMANAGESERVICES);
        HairdresserManageServicesView2 view = (HairdresserManageServicesView2) Facade2.getInstance().getViewMap().get(ViewLayout2.HAIRDRESSERMANAGESERVICES);
        HairdresserManageServicesViewController2 viewController = (HairdresserManageServicesViewController2) view.getLoadedViewController2(ViewLayout2.HAIRDRESSERMANAGESERVICES);
        viewController.fillView(shopBeanSecondUI);
        return true;
    }

    @FXML
    public boolean shopPage() {
        setOnPageStyle(ivShopPage);
        Facade2.getInstance().decorateView2(ViewLayout2.HAIRDRESSERMANAGESHOPPAGE);
        HairdresserManageShopPageView2 view = (HairdresserManageShopPageView2) Facade2.getInstance().getViewMap().get(ViewLayout2.HAIRDRESSERMANAGESHOPPAGE);
        HairdresserManageShopPageViewController2 viewController = (HairdresserManageShopPageViewController2) view.getLoadedViewController2(ViewLayout2.HAIRDRESSERMANAGESHOPPAGE);
        viewController.fillView(shopBeanSecondUI);
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

    public void startBean(HairdresserBean hairdresserBeanSecondUI, ShopBeanInterface shopBeanSecondUI){
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
