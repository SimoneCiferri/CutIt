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
    private static final double ON_PAGE_STYLE = 60;
    private static final double NORMAL_STYLE = 40;

    private ShopBeanInterface shopBeanSecondUI;

    @FXML
    private VBox vbLeftBarHairdresser;

    @FXML
    private ImageView ivShopAppointments;

    @FXML
    private ImageView ivShopPromotions;

    @FXML
    private ImageView ivShopServices;

    @FXML
    private ImageView ivShopPage;

    @FXML
    private ImageView ivLogout;

    @FXML
    public boolean shopAppointmentsPage() {
        setOnPageStyle(ivShopAppointments);
        Facade2.getInstance().decorateView2(ViewLayout2.HAIRDRESSERDELETEBOOKEDAPPOINTMENTS);
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
    }

    public void startBean(ShopBeanInterface shopBeanSecondUI){
        this.shopBeanSecondUI = shopBeanSecondUI;
        shopAppointmentsPage();
    }


    private void setOnPageStyle(ImageView imageViewOnPage){
        ivShopAppointments.setFitWidth(NORMAL_STYLE);
        ivShopAppointments.setFitHeight(NORMAL_STYLE);
        ivShopPromotions.setFitWidth(NORMAL_STYLE);
        ivShopPromotions.setFitHeight(NORMAL_STYLE);
        ivShopServices.setFitWidth(NORMAL_STYLE);
        ivShopServices.setFitHeight(NORMAL_STYLE);
        ivShopPage.setFitWidth(NORMAL_STYLE);
        ivShopPage.setFitHeight(NORMAL_STYLE);
        ivLogout.setFitWidth(NORMAL_STYLE);
        ivLogout.setFitHeight(NORMAL_STYLE);

        imageViewOnPage.setFitWidth(ON_PAGE_STYLE);
        imageViewOnPage.setFitHeight(ON_PAGE_STYLE);
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
