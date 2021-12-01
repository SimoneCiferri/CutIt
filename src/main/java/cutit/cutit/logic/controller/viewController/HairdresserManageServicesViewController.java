package cutit.cutit.logic.controller.viewController;

import cutit.cutit.logic.bean.ManageServiceBean;
import cutit.cutit.logic.controller.applController.ManageServicesController;
import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.facade.Facade;
import javafx.fxml.FXML;

import java.security.Provider;

public class HairdresserManageServicesViewController {

    private ManageServiceBean serviceBean;
    private ManageServicesController manageServicesController;

    @FXML
    public void initialize(){
        serviceBean = new ManageServiceBean();
        manageServicesController = new ManageServicesController();
    }

    @FXML
    public boolean goServH(){
        Facade.getInstance().decorateView(ViewLayout.HAIRDRESSERSERVICES);
        return true;
    }

    @FXML
    public void addService(){
        //"riempio" la Bean con i nuovi valori (usando i setter) e poi la passo al controller applicativo
        manageServicesController.addService(this.serviceBean);
    }

}
