package cutit.cutit.logic.model;

import javafx.scene.layout.BorderPane;

import java.time.LocalDateTime;

public class Promotion {

    private String code;
    private String promName;
    private Integer offValue;
    private LocalDateTime expireDate;
    private String serviceName;

    //costruttore da ricontrollare bene
    public Promotion(String code, String promName, Integer offValue, LocalDateTime expireDate, String serviceName){
        setCode(code);
        setPromName(promName);
        setOffValue(offValue);
        setExpireDate(expireDate);
        setServiceName(serviceName);
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
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
