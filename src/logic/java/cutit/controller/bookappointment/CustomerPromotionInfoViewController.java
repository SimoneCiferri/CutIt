package cutit.controller.bookappointment;

import cutit.bean.interfaces.CustomerBeanInterface;
import cutit.bean.interfaces.PromotionBeanInterface;
import cutit.decorator.decorator1.ViewLayout1;
import cutit.decorator.decorator1.concrete_decorator.ShopInfoView1;
import cutit.facade.Facade;
import cutit.factory.AlertFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;



public class CustomerPromotionInfoViewController {

    private PromotionBeanInterface promotionBean;
    private CustomerBeanInterface customerBeanFirstUI;
    private static final String WARNING_TITLE = "Warning";

    @FXML
    private Label lblShopName;

    @FXML
    private Label lblOffValue;

    @FXML
    private Label lblServiceName;

    @FXML
    private Label lblExpireDate;

    @FXML
    private Label lblPromotionCode;

    public boolean initialize(){
        return true;
    }

    @FXML
    public boolean goBackToPromC(){
        Facade.getInstance().decorateView(ViewLayout1.CUSTOMERPROMOTIONS);
        return true;
    }

    @FXML
    public boolean goShopFromProm(){
        Facade.getInstance().decorateView(ViewLayout1.SHOPINFO);
        ShopInfoView1 view = (ShopInfoView1) Facade.getInstance().getViewMap().get(ViewLayout1.SHOPINFO);
        ShopInfoViewController viewController = (ShopInfoViewController) view.getLoadedViewController1(ViewLayout1.SHOPINFO);
        viewController.fillView(customerBeanFirstUI, promotionBean.getShopName());
        return true;
    }

    @FXML
    public void copyPromotion(){
        try{
        Clipboard clipboard = java.awt.Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection text = new StringSelection(lblPromotionCode.getText());
        clipboard.setContents(text,text);
    }
        catch (AWTError error){
            Alert alert = AlertFactory.getInstance().createAlert(Alert.AlertType.WARNING, WARNING_TITLE, "Impossible to copy promotion, try again later or copying manually");
            alert.show();
        }
    }

    public void fillView(CustomerBeanInterface customerBean, PromotionBeanInterface promotionBean){
        this.promotionBean = promotionBean;
        this.customerBeanFirstUI = customerBean;
        lblShopName.setText(promotionBean.getShopName());
        lblOffValue.setText(promotionBean.getOffValue().toString());
        lblServiceName.setText(promotionBean.getServiceName());
        lblExpireDate.setText(promotionBean.getExpireDate().toString());
        lblPromotionCode.setText(promotionBean.getPromotionCode());
    }
}
