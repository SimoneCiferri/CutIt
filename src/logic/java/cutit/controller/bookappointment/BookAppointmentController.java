package cutit.controller.bookappointment;

import cutit.bean.*;
import cutit.bean.AppointmentBean;
import cutit.bean.ShopBean;
import cutit.controller.addshoptofavourites.AddShopToFavouritesController;
import cutit.controller.getlocationdirections.GetLocationDirectionsController;
import cutit.controller.getlocationdirections.GetLocationDirectionsGoogleMapsViewControllerInterface;
import cutit.controller.payonline.PayOnlineController;
import cutit.database.dao.*;
import cutit.exception.*;
import cutit.log.LogWriter;
import cutit.model.*;
import cutit.utils.ListFromModelList;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookAppointmentController {

    public void bookAppointment(AppointmentBeanInterface appointmentBean) throws DBConnectionException, SQLException, RecordNotFoundException, IOException, DuplicatedRecordException, PaymentException {
        try {
            Customer customer = CustomerDAO.getCustomer(appointmentBean.getCustomer());
            Service service = ServiceDAO.getService(appointmentBean.getShopName(), appointmentBean.getServiceName());
            Shop shop = ShopDAO.getShopFromName(appointmentBean.getShopName());
            Appointment appointment = new Appointment(appointmentBean.getStartTime(), appointmentBean.getStartTime().plusMinutes(30), customer, service, shop);
            if(appointmentBean.getPromotionCode() != null){
                Promotion promotion = PromotionDAO.getPromotion(appointmentBean.getPromotionCode());
                appointment.setPromotion(promotion);
            }
            if(payAppointment(appointmentBean)){
                AppointmentDAO.insertAppointment(appointment);
            }else{
                throw new PaymentException("Payment rejected.");
            }
        } catch (DBConnectionException | SQLException | IOException e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            throw e;
        }
    }

    public void getPersonalPromotions(CustomerBean bean) throws DBConnectionException, SQLException, RecordNotFoundException {
        try {
            List<Promotion> personalProm = PromotionDAO.getAllPersonalPromotions(bean.getcEmail());
            bean.setAllPersonalPromotions(ListFromModelList.getStringListFromPromotions(personalProm));
        } catch ( DBConnectionException | SQLException | RecordNotFoundException dbe){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + dbe.getMessage());
            throw dbe;
        }
    }

    private Boolean payAppointment(AppointmentBeanInterface appBean){
        PayOnlineController payOnlineController = new PayOnlineController();
        return payOnlineController.payAppointment(appBean);
    }

    public Boolean addShopToFavourites(String customerEmail, String shopName) throws DBConnectionException, SQLException, DuplicatedRecordException {
        try {
            AddShopToFavouritesController addShopToFavouritesController = new AddShopToFavouritesController();
            addShopToFavouritesController.addToFavourites(customerEmail, shopName);
            return true;
        } catch( DBConnectionException | SQLException dbe) {
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + dbe.getMessage());
            throw dbe;
        }
    }

    public Boolean getShopDirections(GetLocationDirectionsGoogleMapsViewControllerInterface viewController, ShopBeanInterface bean){
        GetLocationDirectionsController getLocationDirectionsController = new GetLocationDirectionsController();
        getLocationDirectionsController.getDirection(viewController, bean);
        return true;
    }

    public void getShops(ShopListBean shopListBean) throws DBConnectionException, SQLException, IOException{
        try {
            List<Shop> shopList = ShopDAO.getDefaultShops();
            shopListBean.setShopBeanList(beanListFromShopList(shopList));
        } catch (DBConnectionException | SQLException | IOException dbe){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + dbe.getMessage());
            throw dbe;
        }
    }

    public void getShop(ShopBeanInterface shopBean, String shopName) throws  DBConnectionException, SQLException, RecordNotFoundException, IOException {
        try {
            Shop shop = ShopDAO.getShopFromName(shopName);
            shopBean.setShopName(shop.getShopName());
            shopBean.setShopPIVA(shop.getpIVA());
            shopBean.setShopAddress(shop.getAddress());
            shopBean.setShopPhoneNumber(shop.getPhoneNumber());
            shopBean.setShopEmployee(shop.getEmployee());
            shopBean.setShopDescription(shop.getDescription());
            shopBean.setShopOpenTime(shop.getOpenTime());
            shopBean.setShopCloseTime(shop.getCloseTime());
            shopBean.setShopOpenDays(shop.getOpenDays());
            shopBean.setPromotions(ListFromModelList.getStringListFromPromotions(shop.getPromotions()));
            shopBean.setServices(ListFromModelList.getStringListFromServices(shop.getServices()));
            shopBean.setImages(shop.getImages());
        } catch ( DBConnectionException | SQLException | IOException dbe){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + dbe.getMessage());
            throw dbe;
        }
    }

    public void getPromotion(PromotionBean bean) throws DBConnectionException, SQLException, RecordNotFoundException {
        try {
            Promotion promotion = PromotionDAO.getPromotion(bean.getPromotionCode());
            bean.setOffValue(promotion.getOffValue());
            bean.setExpireDate(promotion.getExpireDate());
            bean.setServiceName(promotion.getService().getServiceName());
            bean.setShopName(promotion.getService().getShopName());
        } catch (DBConnectionException | SQLException dbe) {
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n" + dbe.getMessage());
            throw  dbe;
        }

    }

    public void getAppointments(CustomerBean customerBean) throws DBConnectionException, SQLException, RecordNotFoundException {
        try {
            Customer customer = CustomerDAO.getCustomer(new User(customerBean.getcEmail(), customerBean.getcPassword(), customerBean.getcRole()));
            List<Appointment> appList = customer.getBookedAppointments();
            customerBean.setAllBookedAppointments(appBeanListFromAppList(appList));
        } catch ( DBConnectionException | SQLException dbe){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + dbe.getMessage());
            throw dbe;
        }
    }

    public void getAvailableSlots(AppointmentBeanInterface bean, String shopName) throws WrongInputDataException, DBConnectionException, SQLException, RecordNotFoundException, IOException {
        try {
            if(bean.getSelectedDay().isBefore(LocalDate.now())){throw new WrongInputDataException("Impossible to select a past day.");}
            Shop shop = ShopDAO.getShopFromName(shopName);
            LocalTime open = shop.getOpenTime();
            LocalTime close = shop.getCloseTime();
            List<Appointment> allAppList = shop.getAllAppointments();
            List<Appointment> appList = filterByDay(allAppList, bean.getSelectedDay());
            List<LocalTime> availableList = getSlots(appList, open, close, new ArrayList<>());
            bean.setAvailableSlots(availableList);
        } catch ( DBConnectionException | SQLException | IOException dbe){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + dbe.getMessage());
            throw dbe;
        }
    }

    public void getAvailableServices(AppointmentBeanInterface bean, String shopName) throws DBConnectionException, SQLException, RecordNotFoundException, IOException{
        try {
            Shop shop = ShopDAO.getShopFromName(shopName);
            List<Service> servicesList = shop.getServices();
            bean.setAvailableServices(ListFromModelList.getStringListFromServices(servicesList));
        } catch ( DBConnectionException | SQLException | IOException dbe){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + dbe.getMessage());
            throw dbe;
        }
    }

    private List<Appointment> filterByDay(List<Appointment> allAppList, LocalDate day) {
        List<Appointment> list = new ArrayList<>();
        for (Appointment value : allAppList) {
            if (value.getStartTime().toLocalDate().equals(day)) {
                list.add(value);
            }
        }
        return list;
    }

    public void getFavouritesShop(ShopListBean bean, String customerEmail) throws  DBConnectionException, SQLException, RecordNotFoundException, IOException {
        try{
            List<Shop> shopList = ShopDAO.getFavouritesShops(customerEmail);
            bean.setShopBeanList(beanListFromShopList(shopList));
        } catch ( DBConnectionException | SQLException | IOException dbe){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + dbe.getMessage());
            throw dbe;
        }
    }

    public void checkPromotion(AppointmentBeanInterface bean) throws  DBConnectionException, SQLException, RecordNotFoundException, WrongInputDataException {
        try {
            Promotion promotion = PromotionDAO.getPersonalPromotion(bean.getCustomer(), bean.getPromotionCode());
            if(Objects.equals(bean.getServiceName(), promotion.getService().getServiceName())){
                if(!LocalDate.now().isBefore(promotion.getExpireDate())){
                    throw new WrongInputDataException("Selected promotion is expired.");
                }
            } else {
                throw new WrongInputDataException("Selected promotion is not available for selected Service.");
            }
        } catch ( DBConnectionException | SQLException dbe){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + dbe.getMessage());
            throw dbe;
        }
    }

    private List<ShopBeanInterface> beanListFromShopList(List<Shop> shopList) {
        List<ShopBeanInterface> list = new ArrayList<>();
        if(!shopList.isEmpty()){
            for (Shop shop : shopList) {
                String name = shop.getShopName();
                String address = shop.getAddress();
                List<File> images = shop.getImages();
                ShopBeanInterface shopBean = new ShopBean();
                shopBean.setShopName(name);
                shopBean.setShopAddress(address);
                shopBean.setImages(images);
                list.add(shopBean);
            }
        }
        return list;
    }

    private List<AppointmentBeanInterface> appBeanListFromAppList(List<Appointment> allAppointments) {
        List<AppointmentBeanInterface> beanAppList = new ArrayList<>();
        if(!allAppointments.isEmpty()){
            for (Appointment allAppointment : allAppointments) {
                AppointmentBeanInterface bean = new AppointmentBean();
                bean.setStartTime(allAppointment.getStartTime());
                bean.setEndTime(allAppointment.getStartTime().plusMinutes(30));
                bean.setShopName(allAppointment.getShop().getShopName());
                bean.setCustomer(allAppointment.getCustomer().getUserID());
                bean.setServiceName(allAppointment.getService().getServiceName());
                beanAppList.add(bean);
            }
        }
        return beanAppList;
    }

    private List<LocalTime> getSlots(List<Appointment> appList, LocalTime open, LocalTime close, List<LocalTime> availableList){
        LocalTime temp = open;
        if (!appList.isEmpty()) {
            getOnlyFreeSlots(temp, close, availableList, appList);
        } else {
            getAllSlots(temp, close, availableList);
        }
        return availableList;
    }

    private void getOnlyFreeSlots(LocalTime temp, LocalTime close, List<LocalTime> availableList, List<Appointment> appList){
        while (!temp.equals(close)) {
            boolean busy = false;
            for (Appointment appointment : appList) {
                if(appointment.getStartTime().toLocalTime().equals(temp)){
                    busy = true;
                    break;
                }
            }
            if(!busy){availableList.add(temp);}
            temp = temp.plusMinutes(30);
        }
    }

    private void getAllSlots(LocalTime temp, LocalTime close, List<LocalTime> availableList){
        while (!temp.equals(close)) {
            availableList.add(temp);
            temp = temp.plusMinutes(30);
        }
    }
}
