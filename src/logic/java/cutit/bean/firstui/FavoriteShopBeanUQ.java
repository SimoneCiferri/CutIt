package cutit.bean.firstui;

import cutit.bean.FavoriteShopBean;

public class FavoriteShopBeanUQ implements FavoriteShopBean {

    private String customerEmailF;
    private String shopNameF;

    @Override
    public String getCustomerEmailF() {
        return customerEmailF;
    }

    @Override
    public String getShopNameF() {
        return shopNameF;
    }

    @Override
    public void setCustomerEmailF(String customerEmailF) {
        this.customerEmailF = customerEmailF;
    }

    @Override
    public void setShopNameF(String shopNameF) {
       this.shopNameF = shopNameF;
    }
}
