package cutit.controller.bookappointment;

import cutit.bean.CustomerBean;
import cutit.bean.PromotionBean;
import cutit.decorator.ViewLayout1;
import cutit.decorator.concrete_decorator.ShopInfoView1;
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
