package cutit.cutit.logic.bean;

import java.io.File;
import java.util.List;

public class ShopsBean {

    private List<ShopData> shopDataList;

    public List<ShopData> getShopDataList() {
        return shopDataList;
    }

    public void setShopDataList(List<ShopData> shopDataList) {
        this.shopDataList = shopDataList;
    }

    public static class ShopData{

        private String shopName;
        private String address;
        private File profPhoto;

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

        public File getProfPhoto() {
            return profPhoto;
        }

        public void setProfPhoto(File profPhoto) {
            this.profPhoto = profPhoto;
        }
    }

}
