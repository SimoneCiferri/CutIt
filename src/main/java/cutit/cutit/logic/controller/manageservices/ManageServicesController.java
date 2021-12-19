package cutit.cutit.logic.controller.manageservices;

import cutit.cutit.logic.bean.HairdresserBean;
import cutit.cutit.logic.bean.ManageServiceBean;
import cutit.cutit.logic.database.dao.ServiceDAO;
import cutit.cutit.logic.database.dao.ShopDAO;
import cutit.cutit.logic.model.Service;
import cutit.cutit.logic.model.Shop;
import cutit.cutit.logic.model.User;

import java.util.List;

public class ManageServicesController {

    public Boolean addService(ManageServiceBean manageServiceBean, HairdresserBean hairdresserBean) throws Exception {
        User user = new User(hairdresserBean.gethEmail(), hairdresserBean.gethPassword(), 1);
        Shop shop = ShopDAO.getShopFromUser(user);
        manageServiceBean.setServiceShopName(shop.getShopName());
        Service service = new Service(manageServiceBean.getServiceName(), manageServiceBean.getServicePrice(), manageServiceBean.getServiceShopName());
        ServiceDAO.getInstance().insertService(service);
        System.out.println("CONTROLLER APPLICATIVO -> Adding Service (data from ManageServiceBean passed by my viewController)");
        return true;
    }

    public Boolean deleteService(ManageServiceBean manageServiceBean) throws Exception {
        Service service = new Service(manageServiceBean.getServiceName(), manageServiceBean.getServicePrice(), manageServiceBean.getServiceShopName());
        ServiceDAO.getInstance().deleteService(service);
        System.out.println("CONTROLLER APPLICATIVO -> Deleting Service (data from ManageServiceBean passed by my viewController)");
        return true;
    }

    public ManageServiceBean getAllServices(HairdresserBean hairdresserBean) throws Exception {
        User user = new User(hairdresserBean.gethEmail(), hairdresserBean.gethPassword(), 1);
        Shop shop = ShopDAO.getShopFromUser(user);
        List<Service> serviceList = ServiceDAO.getInstance().getALlServices(shop);
        ManageServiceBean manageServiceBean = new ManageServiceBean();
        manageServiceBean.setServicesList(serviceList);
        return manageServiceBean;
    }

}
