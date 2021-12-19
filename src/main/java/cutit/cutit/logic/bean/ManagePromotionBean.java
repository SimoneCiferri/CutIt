package cutit.cutit.logic.bean;

import cutit.cutit.logic.model.Promotion;

import java.util.List;

public class ManagePromotionBean {

    private int promotionCode;
    private List<Promotion> promotionsList;

    public List<Promotion> getPromotionsList() {
        return promotionsList;
    }

    public void setPromotionsList(List<Promotion> promotionsList) {
        this.promotionsList = promotionsList;
    }

    public String getPromotionName(int i){
        return promotionsList.get(i).getPromName();
    }

    public Integer getPromotionOffValue(int i){
        return promotionsList.get(i).getOffValue();
    }

    public int getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(int promotionCode) {
        this.promotionCode = promotionCode;
    }

}
