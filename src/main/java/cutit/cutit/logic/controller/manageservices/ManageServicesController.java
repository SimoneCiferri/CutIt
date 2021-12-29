package cutit.cutit.logic.controller.manageservices;

import cutit.cutit.logic.bean.HairdresserBean;
import cutit.cutit.logic.bean.ManageServiceBean;
import cutit.cutit.logic.database.dao.ServiceDAO;
import cutit.cutit.logic.database.dao.ShopDAO;
import cutit.cutit.logic.model.Hairdresser;
import cutit.cutit.logic.model.Service;
import cutit.cutit.logic.model.Shop;
import cutit.cutit.logic.model.User;

import java.util.List;

public class ManageServicesController {

    public Boolean addService(ManageServiceBean manageServiceBean) throws Exception {
        Service service = new Service(manageServiceBean.getServiceName(), manageServiceBean.getServicePrice(), manageServiceBean.getServiceShopName());
        ServiceDAO.insertService(service);
        System.out.println("CONTROLLER APPLICATIVO -> Adding Service (data from ManageServiceBean passed by my viewController)");
        return true;
    }

    public Boolean deleteService(ManageServiceBean manageServiceBean) throws Exception {
        Service service = new Service(manageServiceBean.getServiceName(), manageServiceBean.getServicePrice(), manageServiceBean.getServiceShopName());
        ServiceDAO.deleteService(service);
        System.out.println("CONTROLLER APPLICATIVO -> Deleting Service (data from ManageServiceBean passed by my viewController)");
        return true;
    }

    public ManageServiceBean getAllServices(HairdresserBean hairdresserBean) throws Exception {
        Shop shop = new Shop(hairdresserBean.getShopName(), hairdresserBean.getpIVA());
        List<Service> serviceList = ServiceDAO.getALlServices(shop);
        ManageServiceBean manageServiceBean = new ManageServiceBean();
        manageServiceBean.setServicesList(serviceList);
        return manageServiceBean;
    }

}
