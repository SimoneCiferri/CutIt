package cutit.controller.manageservices;

import cutit.bean.ManageServiceBeanInterface;
import cutit.bean.ShopBeanInterface;
import cutit.database.dao.ServiceDAO;
import cutit.exception.DBConnectionException;
import cutit.exception.DuplicatedRecordException;
import cutit.log.LogWriter;
import cutit.model.Service;
import cutit.utils.ListFromModelList;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManageServicesController {

    public void addService(ManageServiceBeanInterface manageServiceBean) throws DBConnectionException, SQLException, DuplicatedRecordException {
        try{
            Service service = new Service(manageServiceBean.getServiceName(), manageServiceBean.getServicePrice(), manageServiceBean.getServiceShopName());
            ServiceDAO.insertService(service);
        } catch (DBConnectionException | SQLException e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            throw e;
        }
    }

    public void deleteService(ManageServiceBeanInterface manageServiceBean) throws DBConnectionException, SQLException {
        try{
            Service service = new Service(manageServiceBean.getServiceName(), manageServiceBean.getServicePrice(), manageServiceBean.getServiceShopName());
            ServiceDAO.deleteService(service);
        } catch (DBConnectionException | SQLException e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            throw e;
        }
    }

    public void getAllServices(ManageServiceBeanInterface manageServiceBean, ShopBeanInterface shopBean) throws DBConnectionException, SQLException {
        try{
            List<Service> serviceList = ServiceDAO.getAllServices(shopBean.getShopName());
            manageServiceBean.setAllServicesList(ListFromModelList.getStringListFromServices(serviceList));
            manageServiceBean.setServicesList(mapFromServList(serviceList));
        } catch (DBConnectionException | SQLException e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            throw e;
        }
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
