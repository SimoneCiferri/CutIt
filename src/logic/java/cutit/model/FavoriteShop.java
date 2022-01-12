package cutit.model;

public class FavoriteShop {

    private String customerEmailF;
    private String shopNameF;

    public FavoriteShop(String customerEmailF, String shopNameF){
        setCustomerEmailF(customerEmailF);
        setShopNameF(shopNameF);
    }

    public String getCustomerEmailF() {
        return customerEmailF;
    }

    public void setCustomerEmailF(String customerEmailF) {
        this.customerEmailF = customerEmailF;
    }

    public String getShopNameF() {
        return shopNameF;
    }

    public void setShopNameF(String shopNameF) {
        this.shopNameF = shopNameF;
    }






}
