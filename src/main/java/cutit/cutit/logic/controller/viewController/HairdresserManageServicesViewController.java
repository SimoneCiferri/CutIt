package cutit.cutit.logic.controller.viewController;

import cutit.cutit.logic.bean.ManageServiceBean;
import cutit.cutit.logic.controller.applController.ManageServicesController;
import cutit.cutit.logic.decorator.ViewLayout;
import cutit.cutit.logic.facade.Facade;
import javafx.fxml.FXML;

public class HairdresserManageServicesViewController {

    private ManageServiceBean serviceBean;
    private ManageServicesController manageServicesController;

    @FXML
    public void initialize(){
        serviceBean = new ManageServiceBean();
        manageServicesController = new ManageServicesController();
    }

    @FXML
    public void goServH(){
        Facade.getInstance().decorateView(ViewLayout.HAIRDRESSERSERVICES);
    }

    @FXML
    public void deleteService(){
        //"riempio" la Bean con i nuovi valori (usando i setter) e poi la passo al controller applicativo
        manageServicesController.deleteService(this.serviceBean);
    }

    @FXML
    public void addService(){
        //"riempio" la Bean con i nuovi valori (usando i setter) e poi la passo al controller applicativo
        manageServicesController.addService(this.serviceBean);
    }

}
