package cutit.bean;

import cutit.bean.interfaces.FavoriteShopBeanInterface;

import java.util.List;

public class FavoriteShopBean implements FavoriteShopBeanInterface {

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
