package cutit.controller.manageservices;

import cutit.bean.ManageServiceBean;
import cutit.bean.ShopBean;
import cutit.database.dao.ServiceDAO;
import cutit.exception.DuplicatedRecordException;
import cutit.log.LogWriter;
import cutit.model.Service;
import cutit.model.Shop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManageServicesController {

    public Boolean addService(ManageServiceBean manageServiceBean) throws Exception {
        try{
            Service service = new Service(manageServiceBean.getServiceName(), manageServiceBean.getServicePrice(), manageServiceBean.getServiceShopName());
            ServiceDAO.insertService(service);
            System.out.println("CONTROLLER APPLICATIVO -> Adding Service (data from ManageServiceBean passed by my viewController)");
            return true;
        } catch (DuplicatedRecordException de){
            throw de;
        } catch (Exception e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            throw e;
        }
    }

    public void deleteService(ManageServiceBean manageServiceBean) throws Exception {
        try{
            Service service = new Service(manageServiceBean.getServiceName(), manageServiceBean.getServicePrice(), manageServiceBean.getServiceShopName());
            ServiceDAO.deleteService(service);
            System.out.println("CONTROLLER APPLICATIVO -> Deleting Service (data from ManageServiceBean passed by my viewController)");
        } catch (Exception e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            throw e;
        }

    }

    public void getAllServices(ManageServiceBean manageServiceBean, ShopBean shopBean) throws Exception {
        try{
            Shop shop = new Shop(shopBean.getShopName(), shopBean.getShopPIVA());
            List<Service> serviceList = ServiceDAO.getALlServices(shop);
            manageServiceBean.setAllServicesList(stringListFromServList(serviceList));
            manageServiceBean.setServiceList(mapFromServList(serviceList));
        } catch (Exception e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            throw e;
        }
    }

    private List<String> stringListFromServList(List<Service> serviceList) {
        List<String> servList = new ArrayList<>();
        if(!serviceList.isEmpty()){
            for(int i=0; i<serviceList.size();i++){
                String service = serviceList.get(i).getServiceName();
                servList.add(service);
            }
        }
        return servList;
    }

    private Map<String, Float> mapFromServList(List<Service> serviceList){
        Map<String, Float> services = new HashMap<>();
        if(!serviceList.isEmpty()){
            for(int i=0; i<serviceList.size();i++){
                String service = serviceList.get(i).getServiceName();
                Float price = serviceList.get(i).getPrice();
                services.put(service, price);
            }
        }
        return services;
    }
}
