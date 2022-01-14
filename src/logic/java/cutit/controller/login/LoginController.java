package cutit.controller.login;

import cutit.bean.*;
import cutit.bean.firstui.CustomerBeanFirstUI;
import cutit.bean.firstui.HairdresserBeanFirstUI;
import cutit.database.DBConnection;
import cutit.database.dao.CustomerDAO;
import cutit.database.dao.HairdresserDAO;
import cutit.database.dao.ShopDAO;
import cutit.database.dao.UserDAO;
import cutit.database.query.HairdresserQueries;
import cutit.exception.DuplicatedRecordException;
import cutit.exception.WrongCredentialsException;
import cutit.factory.AlertFactory;
import cutit.log.LogWriter;
import cutit.model.*;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LoginController {

    public boolean login(UserBean bean) throws Exception {
        try{
            User user = new User(bean.getUsername(), bean.getPasswd(), 3);
            UserDAO.userLogin(user);
            System.out.println("        Username = " + bean.getUsername() + " Password = " + bean.getPasswd());
            bean.setRole(user.getRole());
            return true;
        } catch (Exception e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            throw e;
        }
    }

    public boolean signUpCustomer(CustomerBean customerBean) throws Exception {
        try {
            Customer customer = new Customer(customerBean.getcEmail(), customerBean.getcPassword(), 0, customerBean.getcName(), customerBean.getcSurname(), customerBean.getcBirthDate(), customerBean.getcGender());
            if(!UserDAO.checkIfUserExist(customer.getUserID())){
                CustomerDAO.insertCustomer(customer);
                return true;
            }else{
                return false;
            }
        } catch (DuplicatedRecordException de){
            throw de;
        } catch (Exception e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            throw e;
        }

    }

    public Boolean signUpHair(HairdresserBean hairdresserBean) throws Exception {
        try {
            Hairdresser hairdresser = new Hairdresser(hairdresserBean.gethEmail(), hairdresserBean.gethPassword(), 1, hairdresserBean.gethName(), hairdresserBean.gethSurname(), hairdresserBean.getpIVA());
            if (!UserDAO.checkIfUserExist(hairdresser.getUserID())){
                if(!ShopDAO.checkIfShopExists(hairdresserBean.getShopName())){
                    if(!HairdresserDAO.checkIfPIVAExists(hairdresser.getpIVA())){
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
            shopBean.setAddress(hairdresser.getShop().getAddress());
            shopBean.setPhoneNumber(hairdresser.getShop().getPhoneNumber());
            shopBean.setEmployee(hairdresser.getShop().getEmployee());
            shopBean.setShopDescription(hairdresser.getShop().getDescription());
            shopBean.setOpenTime(hairdresser.getShop().getOpenTime());
            shopBean.setCloseTime(hairdresser.getShop().getCloseTime());
            shopBean.setOpenDays(hairdresser.getShop().getOpenDays());
            shopBean.setPromotions(stringListFromPromList(hairdresser.getShop().getPromotions()));
            shopBean.setServices(stringListFromServList(hairdresser.getShop().getServices()));
            shopBean.setAllAppointments(stringListFromAppList(hairdresser.getShop().getAllAppointments()));
            shopBean.setImages(hairdresser.getShop().getImages());
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
            customerBean.setAllPromotions(stringListFromPromList(customer.getPromotions()));
            //customerBean.setAllBookedAppointments(stringListFromAppList(customer.getBookedAppointments()));
        } catch (Exception e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            throw e;
        }
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
