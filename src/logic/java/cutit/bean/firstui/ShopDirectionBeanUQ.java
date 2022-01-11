package cutit.bean.firstui;

import cutit.bean.ShopDirectionBean;

public class ShopDirectionBeanUQ implements ShopDirectionBean {

    private String shopName;
    private String address;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
