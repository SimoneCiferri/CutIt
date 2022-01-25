package cutit.controller.payonline;

import cutit.bean.AppointmentBean;
import cutit.bean.PayOnlineBean;

import java.security.SecureRandom;
import java.util.Random;

public class PayOnlineController {

    private static final Random random = new Random();

    public Boolean payAppointment(AppointmentBean appBean){
        return pay(appBean.getCustomer());
    }

    private boolean pay(String customerEmail) {
        int upperbound = 100;
        int number = random.nextInt(upperbound);
        number = number + customerEmail.length();
        return ((number%2) == 0);
    }



}
