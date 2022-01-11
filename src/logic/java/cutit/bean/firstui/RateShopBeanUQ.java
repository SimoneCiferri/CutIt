package cutit.bean.firstui;

import cutit.bean.RateShopBean;

public class RateShopBeanUQ implements RateShopBean {

    private String shopName;
    private String rCustomer;
    private Integer rate;
    
    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getrCustomer() {
        return rCustomer;
    }

    public void setrCustomer(String rCustomer) {
        this.rCustomer = rCustomer;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }


}
