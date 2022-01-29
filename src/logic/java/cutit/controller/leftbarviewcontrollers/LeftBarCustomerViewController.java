package cutit.controller.leftbarviewcontrollers;

import cutit.bean.interfaces.CustomerBeanInterface;
import cutit.controller.bookappointment.secondui.CustomerAppointmentsViewController2;
import cutit.controller.bookappointment.secondui.CustomerFavouritesShopViewController2;
import cutit.controller.bookappointment.secondui.CustomerPromotionViewController2;
import cutit.controller.bookappointment.secondui.HomeViewController2;
import cutit.decorator.ViewLayout2;
import cutit.decorator.concrete_decorator2.CustomerAppointmentsView2;
import cutit.decorator.concrete_decorator2.CustomerFavouritesShopView2;
import cutit.decorator.concrete_decorator2.CustomerPromotionView2;
import cutit.decorator.concrete_decorator2.HomeView2;
import cutit.facade.Facade2;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;


public class LeftBarCustomerViewController {

    private double xOffset = 0;
    private double yOffset = 0;
    private static final double ON_PAGE_STYLE = 60;
    private static final double NORMAL_STYLE = 40;

    private CustomerBeanInterface customerBeanSecondUI;

    @FXML
    private VBox vbLeftBarCustomer;

    @FXML
    private ImageView ivHome;

    @FXML
    private ImageView ivFavourites;

    @FXML
    private ImageView ivPromotions;

    @FXML
    private ImageView ivAppointments;

    @FXML
    private ImageView ivLogout;

    @FXML
    public boolean homePage() {
        setOnPageStyle(ivHome);
        Facade2.getInstance().decorateView2(ViewLayout2.HOME2);
        HomeView2 view = (HomeView2) Facade2.getInstance().getViewMap().get(ViewLayout2.HOME2);
        HomeViewController2 viewController = (HomeViewController2) view.getLoadedViewController2(ViewLayout2.HOME2);
        viewController.fillView(customerBeanSecondUI);
        return true;
    }

    @FXML
    public boolean favouritesPage() {
        setOnPageStyle(ivFavourites);
        Facade2.getInstance().decorateView2(ViewLayout2.CUSTOMERFAVOURITESSHOP);
        CustomerFavouritesShopView2 view = (CustomerFavouritesShopView2) Facade2.getInstance().getViewMap().get(ViewLayout2.CUSTOMERFAVOURITESSHOP);
        CustomerFavouritesShopViewController2 viewController = (CustomerFavouritesShopViewController2) view.getLoadedViewController2(ViewLayout2.CUSTOMERFAVOURITESSHOP);
        viewController.fillView(customerBeanSecondUI);
        return true;
    }

    @FXML
    public boolean appPage() {
        setOnPageStyle(ivAppointments);
        Facade2.getInstance().decorateView2(ViewLayout2.CUSTOMERAPPOINTMENTS);
        CustomerAppointmentsView2 view = (CustomerAppointmentsView2) Facade2.getInstance().getViewMap().get(ViewLayout2.CUSTOMERAPPOINTMENTS);
        CustomerAppointmentsViewController2 viewController = (CustomerAppointmentsViewController2) view.getLoadedViewController2(ViewLayout2.CUSTOMERAPPOINTMENTS);
        viewController.fillView(customerBeanSecondUI);
        return true;
    }

    @FXML
    public boolean promPage() {
        setOnPageStyle(ivPromotions);
        Facade2.getInstance().decorateView2(ViewLayout2.CUSTOMERPROMOTION);
        CustomerPromotionView2 view = (CustomerPromotionView2) Facade2.getInstance().getViewMap().get(ViewLayout2.CUSTOMERPROMOTION);
        CustomerPromotionViewController2 viewController = (CustomerPromotionViewController2) view.getLoadedViewController2(ViewLayout2.CUSTOMERPROMOTION);
        viewController.fillView(customerBeanSecondUI);
        return true;
    }

    @FXML
    public boolean logout() {
        setOnPageStyle(ivLogout);
        Facade2.getInstance().getStartView2().getPrLayout2().getChildren().remove(vbLeftBarCustomer);
        Facade2.getInstance().logout();
        Facade2.getInstance().decorateView2(ViewLayout2.LEFTBAR);
        Facade2.getInstance().decorateView2(ViewLayout2.LOGIN2);
        return true;
    }

    private void setOnPageStyle(ImageView imageViewOnPage){
        ivHome.setFitWidth(NORMAL_STYLE);
        ivHome.setFitHeight(NORMAL_STYLE);
        ivFavourites.setFitWidth(NORMAL_STYLE);
        ivFavourites.setFitHeight(NORMAL_STYLE);
        ivPromotions.setFitWidth(NORMAL_STYLE);
        ivPromotions.setFitHeight(NORMAL_STYLE);
        ivAppointments.setFitWidth(NORMAL_STYLE);
        ivAppointments.setFitHeight(NORMAL_STYLE);
        ivLogout.setFitWidth(NORMAL_STYLE);
        ivLogout.setFitHeight(NORMAL_STYLE);

        imageViewOnPage.setFitWidth(ON_PAGE_STYLE);
        imageViewOnPage.setFitHeight(ON_PAGE_STYLE);
    }


    public void startBean(CustomerBeanInterface customerBeanSecondUI){
        this.customerBeanSecondUI = customerBeanSecondUI;
        appPage();
    }

    @FXML
    public void closeIV(){
        Stage stage = (Stage)vbLeftBarCustomer.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void reduce(){
        Stage stage = (Stage)vbLeftBarCustomer.getScene().getWindow();
        stage.setIconified(true);
    }


    @FXML
    void getOffset(MouseEvent event) {
        xOffset = vbLeftBarCustomer.getScene().getWindow().getX() - event.getScreenX();
        yOffset = vbLeftBarCustomer.getScene().getWindow().getY() - event.getScreenY();
    }

    @FXML
    void setOff(MouseEvent event) {
        vbLeftBarCustomer.getScene().getWindow().setX(event.getScreenX() + xOffset);
        vbLeftBarCustomer.getScene().getWindow().setY(event.getScreenY() + yOffset);
    }
}
