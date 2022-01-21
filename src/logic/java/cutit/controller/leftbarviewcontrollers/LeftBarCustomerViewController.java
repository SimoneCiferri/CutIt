package cutit.controller.leftbarviewcontrollers;

import com.mysql.cj.util.DnsSrv;
import cutit.bean.CustomerBean;
import cutit.decorator.ViewLayout2;
import cutit.facade.Facade2;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

import java.util.List;

public class LeftBarCustomerViewController {

    private double xOffset = 0;
    private double yOffset = 0;
    private static final double onPageStyle = 60;
    private static final double normalStyle = 40;

    private CustomerBean customerBeanSecondUI;

    @FXML
    private VBox vbLeftBarCustomer;

    @FXML
    private ImageView ivHome, ivFavourites, ivPromotions, ivAppointments, ivLogout;

    @FXML
    public void initialize(){
        System.out.println("LeftBarCustomer viewcontroller");
    }

    @FXML
    public boolean homePage() {
        setOnPageStyle(ivHome);
        Facade2.getInstance().decorateView2(ViewLayout2.HOME2);
        /*Home1View view = (Home1View)Facade.getInstance().getViewMap().get(ViewLayout1.HOME);
        HomeViewController viewController = (HomeViewController) view.getLoadedViewController1(ViewLayout1.HOME);
        viewController.fillView(customerBeanFirstUI);*/
        return true;
    }

    @FXML
    public boolean favouritesPage() {
        setOnPageStyle(ivFavourites);
        Facade2.getInstance().decorateView2(ViewLayout2.CUSTOMERFAVOURITESSHOP);
        /*CustomerFavouritesShopView1 view = (CustomerFavouritesShopView1) Facade.getInstance().getViewMap().get(ViewLayout1.FAVSHOP);
        CustomerFavouritesShopViewController viewController = (CustomerFavouritesShopViewController) view.getLoadedViewController1(ViewLayout1.FAVSHOP);
        viewController.fillView(customerBeanFirstUI);*/
        return true;
    }

    @FXML
    public boolean appPage() {
        setOnPageStyle(ivAppointments);
        Facade2.getInstance().decorateView2(ViewLayout2.CUSTOMERAPPOINTMENTS);
        /*CustomerAppointmentsView1 view = (CustomerAppointmentsView1) Facade.getInstance().getViewMap().get(ViewLayout1.CUSTOMERAPPOINTMENTS);
        CustomerAppointmentsViewController viewController =(CustomerAppointmentsViewController) view.getLoadedViewController1(ViewLayout1.CUSTOMERAPPOINTMENTS);
        viewController.fillView(customerBeanFirstUI);*/
        return true;
    }

    @FXML
    public boolean promPage() {
        setOnPageStyle(ivPromotions);
        Facade2.getInstance().decorateView2(ViewLayout2.CUSTOMERPROMOTION);
        /*CustomerPromotionsView1 view =(CustomerPromotionsView1) Facade.getInstance().getViewMap().get(ViewLayout1.CUSTOMERPROMOTIONS);
        CustomerPromotionsViewController viewController = (CustomerPromotionsViewController) view.getLoadedViewController1(ViewLayout1.CUSTOMERPROMOTIONS);
        viewController.fillView(customerBeanFirstUI);*/
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
        ivHome.setFitWidth(normalStyle);
        ivHome.setFitHeight(normalStyle);
        ivFavourites.setFitWidth(normalStyle);
        ivFavourites.setFitHeight(normalStyle);
        ivPromotions.setFitWidth(normalStyle);
        ivPromotions.setFitHeight(normalStyle);
        ivAppointments.setFitWidth(normalStyle);
        ivAppointments.setFitHeight(normalStyle);
        ivLogout.setFitWidth(normalStyle);
        ivLogout.setFitHeight(normalStyle);

        imageViewOnPage.setFitWidth(onPageStyle);
        imageViewOnPage.setFitHeight(onPageStyle);
    }


    public void startBean(CustomerBean customerBeanSecondUI){
        System.out.println("customerBeanSecondUI passedBY LoginViewController2");
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
