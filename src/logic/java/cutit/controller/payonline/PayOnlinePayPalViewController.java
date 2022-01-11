package cutit.controller.payonline;

import cutit.bean.CustomerBean;
import cutit.bean.PayOnlineBean;

import cutit.bean.firstui.PayOnlineBeanUQ;
import javafx.fxml.FXML;


public class PayOnlinePayPalViewController {

    private PayOnlineBean payOnlineBean;


    @FXML
    public void initialize(){

        payOnlineBean = new PayOnlineBeanUQ();
    }

    public void fillView(PayOnlineBean payOnlineBean){
        this.payOnlineBean = payOnlineBean;
        System.out.println("Filling View from PayOnlineBean data passedBY ...");
        String price = payOnlineBean.getServicePrice();
        String name = payOnlineBean.getPaymentShopName();

        //quì riempirò i campi delle TextFile/TextArea/Label dell'fxml grazie ai getter della bean che mi è stata passata in ingresso
        //showClientApp();
    }

}
