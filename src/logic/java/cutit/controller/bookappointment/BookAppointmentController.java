package cutit.controller.bookappointment;

import cutit.bean.*;
import cutit.bean.firstui.AppointmentBeanFirstUI;
import cutit.bean.firstui.CustomerBeanFirstUI;
import cutit.bean.firstui.ShopBeanUQ;
import cutit.controller.addappointmenttocalendar.AddAppointmentToCalendarController;
import cutit.controller.addshoptofavourites.AddShopToFavouritesController;
import cutit.controller.payonline.PayOnlineController;
import cutit.controller.rateshop.RateShopController;
import cutit.database.dao.AppointmentDAO;
import cutit.database.dao.CustomerDAO;
import cutit.database.dao.ShopDAO;
import cutit.model.*;

import java.util.ArrayList;
import java.util.List;

public class BookAppointmentController {

    private PayOnlineController payOnlineController;
    private RateShopController rateShopController;
    private AddShopToFavouritesController addShopToFavouritesController;
    private AddAppointmentToCalendarController addAppointmentToCalendarController;

    public Boolean compileAppointment(AppointmentBeanFirstUI appointmentBeanFirstUI){
        //dovrò passare le beans, in modo che queste si possano registrare come osservatori del model (e forse anche per prendere i dati in ingresso, oopure li metto da qui ma sempre usando la bean)
        System.out.println("CONTROLLER APPLICATIVO -> Compiling Appointment (data from AppointmentBean passed by my viewController)");
        if(payAppointment(appointmentBeanFirstUI)){
            return true;
        }
        return false;
    }

    private Boolean payAppointment(AppointmentBeanFirstUI appBean){
        payOnlineController = new PayOnlineController();
        payOnlineController.payAppointment(appBean);
        return true;
    }

    public Boolean rateShop(RateShopBean rateShopBean){
        rateShopController = new RateShopController();
        rateShopController.rateShop(rateShopBean);
        return true;
    }

    public Boolean addShopToFavourites(String shopName, String daGiulio) throws Exception {
        addShopToFavouritesController = new AddShopToFavouritesController();
        addShopToFavouritesController.addToFavourites(shopName, daGiulio);
        return true;
    }

    public Boolean addToCalendar(AppointmentBeanFirstUI appBean){
        addAppointmentToCalendarController = new AddAppointmentToCalendarController();
        addAppointmentToCalendarController.addToCalendar(appBean);
        return true;
    }

    public void getAppointments(CustomerBeanFirstUI customerBeanFirstUI) throws Exception {
        Customer customer = CustomerDAO.getCustomer(new User(customerBeanFirstUI.getcEmail(), customerBeanFirstUI.getcPassword(), customerBeanFirstUI.getcRole()));
        List<Appointment> appList = AppointmentDAO.getAllCustomerAppointments(customer);
    }

    public void getShops(ShopListBean shopListBean) throws Exception{
        List<Shop> shopList = ShopDAO.getShops();
        shopListBean.setShopBeanList(beanListFromShopList(shopList));
    }

    public ShopBeanUQ getShop(String shopName) throws Exception {
        Shop shop = ShopDAO.getShopFromName(shopName);
        ShopBeanUQ shopBeanUQ = new ShopBeanUQ();
        shopBeanUQ.setShopName(shop.getShopName());
        shopBeanUQ.setShopPIVA(shop.getpIVA());
        shopBeanUQ.setAddress(shop.getAddress());
        shopBeanUQ.setPhoneNumber(shop.getPhoneNumber());
        shopBeanUQ.setEmployee(shop.getEmployee());
        shopBeanUQ.setShopDescription(shop.getDescription());
        shopBeanUQ.setOpenTime(shop.getOpenTime());
        shopBeanUQ.setCloseTime(shop.getCloseTime());
        shopBeanUQ.setOpenDays(shop.getOpenDays());
        shopBeanUQ.setPromotions(stringListFromPromList(shop.getPromotions()));
        shopBeanUQ.setServices(stringListFromServList(shop.getServices()));
        shopBeanUQ.setImages(shop.getImages());
        return shopBeanUQ;
    }

    /*private List<CustomerBeanFirstUI.AppData> stringAppDataFromAppList(List<Appointment> allAppointments) {
        List<CustomerBeanFirstUI.AppData> appList = new ArrayList<>();
        if(!allAppointments.isEmpty()){
            for(int i = 0; i<allAppointments.size(); i++){
                String startTime = allAppointments.get(i).getStartTime().toString();
                String endTime = allAppointments.get(i).getEndTime().toString();
                String customer = allAppointments.get(i).getCustomer().getUserID();
                String service = allAppointments.get(i).getService().getServiceName();
                String shop = allAppointments.get(i).getShop().getShopName();
                CustomerBeanFirstUI.AppData d = new CustomerBeanFirstUI.AppData();
                d.setAppStarTime(startTime);
                d.setAppEndTime(endTime);
                d.setAppCustomer(customer);
                d.setAppService(service);
                d.setAppShopName(shop);
                appList.add(d);
            }
        }
        return appList;
    }*/

    private List<ShopBean> beanListFromShopList(List<Shop> shopList) {
        List<ShopBean> list = new ArrayList<>();
        if(!shopList.isEmpty()){
            for(int i = 0; i<shopList.size(); i++){
                String name = shopList.get(i).getShopName();
                String address = shopList.get(i).getAddress();
                ShopBean shopBean = new ShopBeanUQ(); //si dovrebbe capire quale creare a runtime OPPURE si usa shopBEanFirstUI come shopBeanUnica per tutte e due le UI
                shopBean.setShopName(name);
                shopBean.setAddress(address);
                list.add(shopBean);
            }
        }
        return list;
    }

    private List<String> stringListFromAppList(List<Appointment> allAppointments) {
        List<String> appList = new ArrayList<>();
        if(!allAppointments.isEmpty()){
            for(int i = 0; i<allAppointments.size(); i++){
                String p = allAppointments.get(i).getStartTime().toString();
                appList.add(p);
            }
        }
        return appList;
    }

    private List<String> stringListFromServList(List<Service> services) {
        List<String> servList = new ArrayList<>();
        if(!services.isEmpty()){
            for(int i = 0; i<services.size(); i++){
                String p = services.get(i).getServiceName();
                servList.add(p);
            }
        }
        return servList;
    }

    private List<String> stringListFromPromList(List<Promotion> promotions) {
        List<String> promList = new ArrayList<>();
        if(!promotions.isEmpty()){
            for(int i = 0; i<promotions.size(); i++){
                String p = promotions.get(i).getCode();
                promList.add(p);
            }
        }
        return promList;
    }
}
