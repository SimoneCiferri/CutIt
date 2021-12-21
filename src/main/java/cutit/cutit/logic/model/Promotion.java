package cutit.cutit.logic.model;

import javafx.scene.layout.BorderPane;

import java.time.LocalDateTime;
import java.util.List;

public class Promotion {

    private String code;
    private String promName;
    private Integer offValue;
    private LocalDateTime expireDate;
    private Service service;
    private List<Discount> discounts;

    //costruttore da ricontrollare bene
    public Promotion(String code, String promName, Integer offValue, LocalDateTime expireDate){
        setCode(code);
        setPromName(promName);
        setOffValue(offValue);
        setExpireDate(expireDate);
    }

    public Service getServiceName() {
        return service;
    }

    public void setServiceName(Service service) {
        this.service = service;
    }

    public String getPromName() {
        return promName;
    }

    public void setPromName(String promName) {
        this.promName = promName;
    }

    public Integer getOffValue() {
        return offValue;
    }

    public void setOffValue(Integer offValue) {
        this.offValue = offValue;
    }

    public LocalDateTime getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDateTime expireDate) {
        this.expireDate = expireDate;
    }

    public String getCode(){
        return code;
    }

    public void setCode(String code){
        this.code = code;
    }

    //mancano i metodi per l'expirationDate


}
