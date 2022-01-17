package cutit.controller.bookappointment;

import cutit.bean.CustomerBean;
import cutit.bean.PromotionBean;
import cutit.decorator.ViewLayout;
import cutit.decorator.concreteDecorator.ShopInfoView;
import cutit.facade.Facade;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;



public class CustomerPromotionInfoViewController {

    private PromotionBean promotionBean;
    private CustomerBean customerBeanFirstUI;

    @FXML
    private Label lblShopName, lblOffValue, lblServiceName, lblExpireDate, lblPromotionCode;

    public boolean initialize(){
        System.out.println("CONTROLLER GRAFICO CUSTOMERPROMOTIONINFOVIEWCONTROLLER");
        return true;
    }

    @FXML
    public boolean goBackToPromC(){
        Facade.getInstance().decorateView(ViewLayout.CUSTOMERPROMOTIONS);
        return true;
    }

    @FXML
    public boolean goShopFromProm(){
        Facade.getInstance().decorateView(ViewLayout.SHOPINFO);
        ShopInfoView view = (ShopInfoView) Facade.getInstance().getViewMap().get(ViewLayout.SHOPINFO);
        ShopInfoViewController viewController = (ShopInfoViewController) view.getLoadedViewController(ViewLayout.SHOPINFO);
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
            System.out.println("the default Toolkit cannot be initialized");
        }
    }

    public void fillView(CustomerBean customerBean, PromotionBean promotionBean){
        this.promotionBean = promotionBean;
        this.customerBeanFirstUI = customerBean;
        lblShopName.setText(promotionBean.getShopName());
        lblOffValue.setText(promotionBean.getOffValue().toString());
        lblServiceName.setText(promotionBean.getServiceName());
        lblExpireDate.setText(promotionBean.getExpireDate().toString());
        lblPromotionCode.setText(promotionBean.getPromotionCode());
    }
}
