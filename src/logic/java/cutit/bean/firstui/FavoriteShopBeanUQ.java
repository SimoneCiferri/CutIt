package cutit.bean.firstui;

import cutit.bean.FavoriteShopBean;

import java.util.List;

public class FavoriteShopBeanUQ implements FavoriteShopBean {

    private List<String> favShopList;

    @Override
    public List<String> getFavShopList() {
        return favShopList;
    }

    @Override
    public void setFavShopList(List<String> favShopList) {
        this.favShopList = favShopList;
    }
}
