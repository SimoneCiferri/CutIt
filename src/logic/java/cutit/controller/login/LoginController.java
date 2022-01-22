package cutit.controller.login;

import cutit.bean.*;
import cutit.controller.pepper.PepperClass;
import cutit.database.dao.CustomerDAO;
import cutit.database.dao.HairdresserDAO;
import cutit.database.dao.ShopDAO;
import cutit.database.dao.UserDAO;
import cutit.exception.DuplicatedRecordException;
import cutit.exception.WrongCredentialsException;
import cutit.exception.WrongInputDataException;
import cutit.log.LogWriter;
import cutit.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LoginController {

    public boolean login(UserBean bean) throws Exception {
        try{
            User user = new User(bean.getUsername(), bean.getPasswd(), 3);
            UserDAO.userLogin(user);
            bean.setRole(user.getRole());
            return true;
        } catch (WrongCredentialsException wce){
            throw wce;
        } catch (Exception e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            throw e;
        }
    }

    public boolean signUpCustomer(CustomerBean customerBean) throws Exception {
        try {
            if(!customerBean.getcBirthDate().isAfter(LocalDate.now())){
                Customer customer = new Customer(customerBean.getcEmail(), customerBean.getcPassword(), 0, customerBean.getcName(), customerBean.getcSurname(), customerBean.getcBirthDate(), customerBean.getcGender());
                if(!UserDAO.checkUser(customer.getUserID())){
                    CustomerDAO.insertCustomer(customer);
                    return true;
                }else{
                    return false;
                }
            }else{
                throw new WrongInputDataException("Cannot use selected day as the Birthday!");
            }
        } catch (DuplicatedRecordException | WrongInputDataException exception){
            throw exception;
        } catch (Exception e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            throw e;
        }

    }

    public void getCustomer(UserBean userBean, CustomerBean customerBean) throws Exception {
        try {
            User user = new User(userBean.getUsername(), userBean.getPasswd(), userBean.getRole());
            Customer customer = CustomerDAO.getCustomer(user);
            customerBean.setcEmail(customer.getUserID());
            customerBean.setcPassword(customer.getPwd());
            customerBean.setcRole(customer.getRole());
            customerBean.setcName(customer.getName());
            customerBean.setcSurname(customer.getSurname());
            customerBean.setcBirthDate(customer.getBirthDate());
            customerBean.setcGender(customer.getGender());
            customerBean.setAllPersonalPromotions(stringListFromPromList(customer.getPromotions()));
            //customerBean.setAllBookedAppointments(stringListFromAppList(customer.getBookedAppointments()));
        } catch (Exception e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            throw e;
        }
    }

    public Boolean signUpHair(HairdresserBean hairdresserBean) throws Exception {
        try {
            Hairdresser hairdresser = new Hairdresser(hairdresserBean.gethEmail(), hairdresserBean.gethPassword(), 1, hairdresserBean.gethName(), hairdresserBean.gethSurname(), hairdresserBean.getpIVA());
            if (!UserDAO.checkUser(hairdresser.getUserID())){
                if(!ShopDAO.checkShop(hairdresserBean.getShopName())){
                    if(!HairdresserDAO.checkPIVA(hairdresser.getpIVA())){
                        HairdresserDAO.insertNewHairdresser(hairdresser, hairdresserBean.getShopName());
                        return true;
                    }
                }
            }
            return false;
        } catch (DuplicatedRecordException de){
            throw de;
        } catch (Exception e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            throw e;
        }
    }

    public void getHairdresserAndShop(UserBean userBean, HairdresserBean hairdresserBean, ShopBean shopBean) throws Exception {
        try {
            User user = new User(userBean.getUsername(), userBean.getPasswd(), userBean.getRole());
            Hairdresser hairdresser = HairdresserDAO.getHairdresser(user);
            hairdresserBean.sethEmail(hairdresser.getUserID());
            hairdresserBean.sethPassword(hairdresser.getPwd());
            hairdresserBean.sethRole(hairdresser.getRole());
            hairdresserBean.sethName(hairdresser.getName());
            hairdresserBean.sethSurname(hairdresser.getSurname());
            hairdresserBean.setpIVA(hairdresser.getpIVA());
            hairdresserBean.setShopName(hairdresser.getShop().getShopName());
            shopBean.setShopName(hairdresser.getShop().getShopName());
            shopBean.setShopPIVA(hairdresser.getShop().getpIVA());
            shopBean.setShopAddress(hairdresser.getShop().getAddress());
            shopBean.setShopPhoneNumber(hairdresser.getShop().getPhoneNumber());
            shopBean.setShopEmployee(hairdresser.getShop().getEmployee());
            shopBean.setShopDescription(hairdresser.getShop().getDescription());
            shopBean.setShopOpenTime(hairdresser.getShop().getOpenTime());
            shopBean.setShopCloseTime(hairdresser.getShop().getCloseTime());
            shopBean.setShopOpenDays(hairdresser.getShop().getOpenDays());
            shopBean.setPromotions(stringListFromPromList(hairdresser.getShop().getPromotions()));
            shopBean.setServices(stringListFromServList(hairdresser.getShop().getServices()));
            shopBean.setAllAppointments(stringListFromAppList(hairdresser.getShop().getAllAppointments()));
            shopBean.setImages(hairdresser.getShop().getImages());

            pepperFunction(hairdresserBean, shopBean);

        } catch (Exception e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            throw e;
        }
    }

    private void pepperFunction(HairdresserBean hairdresserBean, ShopBean shopBean)  {
        PepperClass p = new PepperClass(hairdresserBean, shopBean);
        p.start();
    }

    private List<String> stringListFromAppList(List<Appointment> allAppointments) { //da vedere, forse settare la lista di bean non di stringhe
        List<String> appList = new ArrayList<>();
        if(!allAppointments.isEmpty()){
            for (Appointment allAppointment : allAppointments) {
                String p = allAppointment.getStartTime().toString();
                appList.add(p);
            }
        }
        return appList;
    }

    private List<String> stringListFromServList(List<Service> services) {
        List<String> servList = new ArrayList<>();
        if(!services.isEmpty()){
            for (Service service : services) {
                String p = service.getServiceName();
                servList.add(p);
            }
        }
        return servList;
    }

    private List<String> stringListFromPromList(List<Promotion> promotions) {
        List<String> promList = new ArrayList<>();
        if(!promotions.isEmpty()){
            for (Promotion promotion : promotions) {
                String p = promotion.getCode();
                promList.add(p);
            }
        }
        return promList;
    }

}
