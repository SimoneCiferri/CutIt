package cutit.bean;

import java.util.ArrayList;
import java.util.List;

public class ShopListBeanUQ implements ShopListBean {

    private List<ShopBeanInterface> shopBeanList = new ArrayList<>();

    public List<ShopBeanInterface> getShopBeanList() {
        return shopBeanList;
    }

    public void setShopBeanList(List<ShopBeanInterface> shopBeanList) {
        this.shopBeanList = shopBeanList;
    }

}
