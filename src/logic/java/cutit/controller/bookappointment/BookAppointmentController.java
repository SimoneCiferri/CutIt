package cutit.controller.bookappointment;

import cutit.bean.*;
import cutit.bean.firstui.AppointmentBeanFirstUI;
import cutit.bean.firstui.ShopBeanUQ;
import cutit.controller.addappointmenttocalendar.AddAppointmentToCalendarController;
import cutit.controller.addshoptofavourites.AddShopToFavouritesController;
import cutit.controller.payonline.PayOnlineController;
import cutit.controller.rateshop.RateShopController;
import cutit.database.dao.*;
import cutit.exception.DuplicatedRecordException;
import cutit.exception.WrongInputDataException;
import cutit.log.LogWriter;
import cutit.model.*;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookAppointmentController {

    private PayOnlineController payOnlineController;
    private RateShopController rateShopController;
    private AddShopToFavouritesController addShopToFavouritesController;
    private AddAppointmentToCalendarController addAppointmentToCalendarController;

    public boolean bookAppointment(AppointmentBean appointmentBean) throws Exception {
        try {
            Customer customer = CustomerDAO.getCustomer(appointmentBean.getCustomer());
            Service service = ServiceDAO.getService(appointmentBean.getShopName(), appointmentBean.getServiceName());
            Shop shop = ShopDAO.getShopFromName(appointmentBean.getShopName());
            Appointment appointment = new Appointment(appointmentBean.getStartTime(), appointmentBean.getStartTime().plusMinutes(30), customer, service, shop);
            if(appointmentBean.getPromotionCode() != null){
                Promotion promotion = PromotionDAO.getPromotion(appointmentBean.getPromotionCode());
                appointment.setPromotion(promotion);
            }
            AppointmentDAO.insertAppointment(appointment);
            return true;
        } catch (DuplicatedRecordException de){
            throw de;
        } catch (Exception e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            throw e;
        }
    }

    private Boolean payAppointment(AppointmentBean appBean){
        payOnlineController = new PayOnlineController();
        return payOnlineController.payAppointment(appBean);
    }

    public Boolean rateShop(RateShopBean rateShopBean){
        rateShopController = new RateShopController();
        rateShopController.rateShop(rateShopBean);
        return true;
    }

    public Boolean addShopToFavourites(String customerEmail, String shopName) throws Exception {
        addShopToFavouritesController = new AddShopToFavouritesController();
        addShopToFavouritesController.addToFavourites(customerEmail, shopName);
        return true;
    }

    public Boolean addToCalendar(AppointmentBean appBean){
        addAppointmentToCalendarController = new AddAppointmentToCalendarController();
        addAppointmentToCalendarController.addToCalendar(appBean);
        return true;
    }

    public void getAppointments(CustomerBean customerBean) throws Exception {
        try {
            Customer customer = CustomerDAO.getCustomer(new User(customerBean.getcEmail(), customerBean.getcPassword(), customerBean.getcRole()));
            List<Appointment> appList = customer.getBookedAppointments();
            customerBean.setAllBookedAppointments(appBeanListFromAppList(appList));
        } catch (Exception e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            throw e;
        }
    }

    public void getAvailableSlots(AppointmentBean bean, String shopName) throws Exception {
        try {
            if(bean.getSelectedDay().isBefore(LocalDate.now())){
                throw new WrongInputDataException("Impossible to select a past day.");
            }
            Shop shop = ShopDAO.getShopFromName(shopName);
            LocalTime open = shop.getOpenTime();
            LocalTime close = shop.getCloseTime();
            List<Appointment> allAppList = shop.getAllAppointments();
            List<Appointment> appList = filterByDay(allAppList, bean.getSelectedDay());
            List<LocalTime> availableList = new ArrayList<>();
            LocalTime temp = open;
            if (!appList.isEmpty()) {
                while (!temp.equals(close)) {
                    System.out.println("Controllo per le ore " + temp);
                    boolean busy = false;
                    for (Appointment appointment : appList) {
                        System.out.println("---Controllo su appuntamento: " + appointment.getStartTime().toLocalTime());
                        if(appointment.getStartTime().toLocalTime().equals(temp)){
                            System.out.println("---Slot " + appointment.getStartTime().toLocalTime() +" occupato");
                            busy = true;
                            break;
                        }
                    }
                    if(!busy){
                        System.out.println("---Slot " + temp + " libero -> aggiungo");
                        availableList.add(temp);
                    }
                    temp = temp.plusMinutes(30);
                }
            } else {
                while (!temp.equals(close)) {
                    availableList.add(temp);
                    temp = temp.plusMinutes(30);
                }
            }
            bean.setAvailableSlots(availableList);
        } catch (WrongInputDataException wde) {
            throw wde;
        } catch (Exception e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            throw e;
        }
    }

    public void getAvailableServices(AppointmentBean bean, String shopName) throws Exception {
        try {
            Shop shop = ShopDAO.getShopFromName(shopName);
            List<Service> servicesList = shop.getServices();
            bean.setAvailableServices(stringListFromServList(servicesList));
        } catch (Exception e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            throw e;
        }
    }

    private List<Appointment> filterByDay(List<Appointment> allAppList, LocalDate day) {
        List<Appointment> list = new ArrayList<>();
        for(int i = 0; i<allAppList.size(); i++){
            if(allAppList.get(i).getStartTime().toLocalDate().equals(day)){
                list.add(allAppList.get(i));
            }
        }
        return list;
    }

    public void getShops(ShopListBean shopListBean) throws Exception{
        try {
            List<Shop> shopList = ShopDAO.getShops();
            shopListBean.setShopBeanList(beanListFromShopList(shopList));
        } catch (Exception e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            throw e;
        }
    }

    public ShopBeanUQ getShop(String shopName) throws Exception {
        try {
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
        } catch (Exception e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            throw e;
        }
    }

    public void getFavouritesShop(ShopListBean bean, String customerEmail) throws Exception {
        try{
            List<Shop> shopList = FavoriteShopsDAO.getFavouritesShops(customerEmail);
            bean.setShopBeanList(beanListFromShopList(shopList));
        } catch (Exception e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            throw e;
        }
    }

    public boolean checkPromotion(AppointmentBean bean) throws Exception {
        try {
            Customer customer = CustomerDAO.getCustomer(bean.getCustomer());
            List<Promotion> promList = customer.getPromotions();
            for (Promotion promotion : promList) {
                if (Objects.equals(promotion.getCode(), bean.getPromotionCode())) {
                    return true;
                }
            }
            return false;
        } catch (Exception e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            throw e;
        }
    }

    private List<ShopBean> beanListFromShopList(List<Shop> shopList) {
        List<ShopBean> list = new ArrayList<>();
        if(!shopList.isEmpty()){
            for(int i = 0; i<shopList.size(); i++){
                String name = shopList.get(i).getShopName();
                String address = shopList.get(i).getAddress();
                List<File> images = shopList.get(i).getImages();
                ShopBean shopBean = new ShopBeanUQ(); //si dovrebbe capire quale creare a runtime OPPURE si usa shopBEanFirstUI come shopBeanUnica per tutte e due le UI
                shopBean.setShopName(name);
                shopBean.setAddress(address);
                shopBean.setImages(images);
                list.add(shopBean);
            }
        }
        return list;
    }

    private List<AppointmentBean> appBeanListFromAppList(List<Appointment> allAppointments) {
        List<AppointmentBean> beanAppList = new ArrayList<>();
        if(!allAppointments.isEmpty()){
            for(int i = 0; i<allAppointments.size(); i++){
                AppointmentBean bean = new AppointmentBeanFirstUI();
                bean.setStartTime(allAppointments.get(i).getStartTime());
                bean.setEndTime(allAppointments.get(i).getStartTime().plusMinutes(30));
                bean.setShopName(allAppointments.get(i).getShop().getShopName());
                bean.setCustomer(allAppointments.get(i).getCustomer().getUserID());
                bean.setServiceName(allAppointments.get(i).getService().getServiceName());
                beanAppList.add(bean);
            }
        }
        return beanAppList;
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
