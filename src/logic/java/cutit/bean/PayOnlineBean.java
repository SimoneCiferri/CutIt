package cutit.bean;

import cutit.bean.interfaces.PayOnlineBeanInterface;

public class PayOnlineBean implements PayOnlineBeanInterface {

    private String paymentShopName;
    private String servicePrice;

    @Override
    public String getPaymentShopName() {
        return paymentShopName;
    }

    @Override
    public String getServicePrice() {
        return servicePrice;
    }

    public void setPaymentShopName(String paymentShopName) {
        this.paymentShopName = paymentShopName;
    }

    public void setServicePrice(String servicePrice) {
        this.servicePrice = servicePrice;
    }
}
