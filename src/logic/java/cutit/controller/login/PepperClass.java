package cutit.controller.login;

import cutit.bean.HairdresserBean;
import cutit.bean.ShopBean;
import cutit.database.dao.AppointmentDAO;
import cutit.database.dao.ShopDAO;
import cutit.model.Appointment;
import cutit.model.Customer;
import cutit.model.Shop;

import java.util.List;

public class PepperClass extends Thread{

    private HairdresserBean hairdresserBean;
    private ShopBean shopBean;

    public PepperClass(HairdresserBean hairdresserBean, ShopBean shopBean){
        this.hairdresserBean = hairdresserBean;
        this.shopBean = shopBean;
    }

    public void run(){
        //Lista di tutti i miei appuntamenti
        try {
            Shop shop = ShopDAO.getShopFromName(hairdresserBean.getShopName());
            List<Appointment> allShopAppointments = AppointmentDAO.getAllShopAppointments(shop);
            if(!allShopAppointments.isEmpty()){
                //
            } else{
                System.out.println("No Appointments booked!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //List<Customer> allShopCustomer = ShopDAO
        while (true){
            System.out.println("-----------Thread attivo per " + hairdresserBean.gethEmail() + shopBean.getShopName());
            try {
                sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }

















}
