package cutit.cutit.logic.model.entity;

public class Coupon {

    private String promotionCode;
    private Float value;

    //manca il costruttore, i metodi per promotionCode e forse qualche altro attributo

    public Float getValue(){
        return value;
    }

    public void setValue(Float value){
        this.value = value;
    }

}
