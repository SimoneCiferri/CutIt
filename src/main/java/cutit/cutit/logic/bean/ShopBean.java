package cutit.cutit.logic.bean;

import javafx.scene.image.Image;
import java.util.Map;

public class ShopBean {

    private String shopName;
    private String shopAddress;
    private String shopWorkers;
    private String shopDescription;
    private Image images;
    private Map openDays;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getShopWorkers() {
        return shopWorkers;
    }

    public void setShopWorkers(String shopWorkers) {
        this.shopWorkers = shopWorkers;
    }

    public String getShopDescription() {
        return shopDescription;
    }

    public void setShopDescription(String shopDescription) {
        this.shopDescription = shopDescription;
    }

    public Image getImages() {
        return images;
    }

    public void setImages(Image images) {
        this.images = images;
    }

    public Map getOpenDays() {
        return openDays;
    }

    public void setOpenDays(Map openDays) {
        this.openDays = openDays;
    }






}
