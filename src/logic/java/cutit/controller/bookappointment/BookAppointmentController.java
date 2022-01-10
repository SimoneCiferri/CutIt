package cutit.controller.bookappointment;

import cutit.bean.*;
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

    public Boolean compileAppointment(AppointmentBean appointmentBean){
        //dovrò passare le beans, in modo che queste si possano registrare come osservatori del model (e forse anche per prendere i dati in ingresso, oopure li metto da qui ma sempre usando la bean)
        System.out.println("CONTROLLER APPLICATIVO -> Compiling Appointment (data from AppointmentBean passed by my viewController)");
        if(payAppointment(appointmentBean)){
            return true;
        }
        return false;
    }

    private Boolean payAppointment(AppointmentBean appBean){
        payOnlineController = new PayOnlineController();
        payOnlineController.payAppointment(appBean);
        return true;
    }

    public Boolean rateShop(RateShopBean rateShopBean){
        rateShopController = new RateShopController();
        rateShopController.rateShop(rateShopBean);
        return true;
    }

    public Boolean addShopToFavourites(RateShopBean shopBean){
        addShopToFavouritesController = new AddShopToFavouritesController();
        addShopToFavouritesController.addToFavourites(shopBean);
        return true;
    }

    public Boolean addToCalendar(AppointmentBean appBean){
        addAppointmentToCalendarController = new AddAppointmentToCalendarController();
        addAppointmentToCalendarController.addToCalendar(appBean);
        return true;
    }

    public void getAppointments(CustomerBean customerBean) throws Exception {
        Customer customer = CustomerDAO.getCustomer(new User(customerBean.getcEmail(), customerBean.getcPassword(), customerBean.getcRole()));
        List<Appointment> appList = AppointmentDAO.getAllCustomerAppointments(customer);
        customerBean.setAllBookedAppointment(stringAppDataFromAppList(appList));
    }

    public ShopsBean getShops() throws Exception{
        List<Shop> shopList = ShopDAO.getShops();
        ShopsBean shopsBean = new ShopsBean();
        shopsBean.setShopDataList(shopDataFromShopList(shopList));
        return shopsBean;
    }

    public ShopBean getShop(String shopName) throws Exception {
        Shop shop = ShopDAO.getShopFromName(shopName);
        ShopBean shopBean = new ShopBean();
        shopBean.setShopName(shop.getShopName());
        shopBean.setShopPIVA(shop.getpIVA());
        shopBean.setAddress(shop.getAddress());
        shopBean.setPhoneNumber(shop.getPhoneNumber());
        shopBean.setEmployee(shop.getEmployee());
        shopBean.setShopDescription(shop.getDescription());
        shopBean.setOpenTime(shop.getOpenTime());
        shopBean.setCloseTime(shop.getCloseTime());
        shopBean.setOpenDays(shop.getOpenDays());
        shopBean.setPromotions(stringListFromPromList(shop.getPromotions()));
        shopBean.setServices(stringListFromServList(shop.getServices()));
        shopBean.setImages(shop.getImages());
        return shopBean;
    }

    private List<ShopsBean.ShopData> shopDataFromShopList(List<Shop> shopList) {
        List<ShopsBean.ShopData> list = new ArrayList<>();
        if(!shopList.isEmpty()){
            for(int i = 0; i<shopList.size(); i++){
                String shopName = shopList.get(i).getShopName();
                String address = shopList.get(i).getAddress();
                ShopsBean.ShopData data = new ShopsBean.ShopData();
                data.setShopName(shopName);
                data.setAddress(address);
                list.add(data);
            }
        }
        return list;
    }

    private List<CustomerBean.AppData> stringAppDataFromAppList(List<Appointment> allAppointments) {
        List<CustomerBean.AppData> appList = new ArrayList<>();
        if(!allAppointments.isEmpty()){
            for(int i = 0; i<allAppointments.size(); i++){
                String startTime = allAppointments.get(i).getStartTime().toString();
                String endTime = allAppointments.get(i).getEndTime().toString();
                String customer = allAppointments.get(i).getCustomer().getUserID();
                String service = allAppointments.get(i).getService().getServiceName();
                String shop = allAppointments.get(i).getShop().getShopName();
                CustomerBean.AppData d = new CustomerBean.AppData();
                d.setAppStarTime(startTime);
                d.setAppEndTime(endTime);
                d.setAppCustomer(customer);
                d.setAppService(service);
                d.setAppShopName(shop);
                appList.add(d);
            }
        }
        return appList;
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
