package cutit.bean.firstui;

import cutit.bean.PayOnlineBean;

public class PayOnlineBeanUQ implements PayOnlineBean{

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
