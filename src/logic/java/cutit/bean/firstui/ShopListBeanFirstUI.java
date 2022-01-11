package cutit.bean.firstui;

import cutit.bean.ShopBean;
import cutit.bean.ShopListBean;

import java.util.List;

public class ShopListBeanFirstUI implements ShopListBean {

    private List<ShopBean> shopBeanList;

    public List<ShopBean> getShopBeanList() {
        return shopBeanList;
    }

    public void setShopBeanList(List<ShopBean> shopBeanList) {
        this.shopBeanList = shopBeanList;
    }

}
