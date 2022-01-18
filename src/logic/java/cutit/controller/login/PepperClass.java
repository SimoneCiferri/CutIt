package cutit.controller.login;

import cutit.bean.HairdresserBean;
import cutit.bean.ShopBean;

public class PepperClass extends Thread{

    private HairdresserBean hairdresserBean;
    private ShopBean shopBean;

    public PepperClass(HairdresserBean hairdresserBean, ShopBean shopBean){
        this.hairdresserBean = hairdresserBean;
        this.shopBean = shopBean;
    }

    public void run(){




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
