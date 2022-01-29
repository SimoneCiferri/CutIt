package cutit.bean;

import cutit.bean.interfaces.ShopBeanInterface;
import cutit.bean.interfaces.ShopListBeanInterface;

import java.util.ArrayList;
import java.util.List;

public class ShopListBean implements ShopListBeanInterface {

    private List<ShopBeanInterface> shopBeanList = new ArrayList<>();

    public List<ShopBeanInterface> getShopBeanList() {
        return shopBeanList;
    }

    public void setShopBeanList(List<ShopBeanInterface> shopBeanList) {
        this.shopBeanList = shopBeanList;
    }

}
