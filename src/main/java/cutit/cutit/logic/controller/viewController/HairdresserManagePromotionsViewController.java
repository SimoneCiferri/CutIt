package cutit.cutit.logic.controller.viewController;

import cutit.cutit.logic.bean.ManagePromotionBean;
import cutit.cutit.logic.bean.ManageServiceBean;
import cutit.cutit.logic.controller.applController.ManagePromotionController;
import cutit.cutit.logic.controller.applController.ManageServicesController;
import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.facade.Facade;
import javafx.fxml.FXML;

public class HairdresserManagePromotionsViewController {

    private ManagePromotionBean managePromotionBean;
    private ManagePromotionController managePromotionController;

    @FXML
    public void initialize(){
        managePromotionBean = new ManagePromotionBean();
        managePromotionController = new ManagePromotionController();
    }

    @FXML
    public void goPromH(){
        Facade.getInstance().decorateView(ViewLayout.HAIRDRESSERPROMOTIONS);
    }

    @FXML
    public void removePromotion(){
        //"riempio" la Bean con i nuovi valori (usando i setter) e poi la passo al controller applicativo
        managePromotionController.removePromotion(this.managePromotionBean);
    }

    @FXML
    public void addPromotion(){
        //"riempio" la Bean con i nuovi valori (usando i setter) e poi la passo al controller applicativo
        managePromotionController.addPromotion(this.managePromotionBean);
    }


}
