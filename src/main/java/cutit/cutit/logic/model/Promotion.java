package cutit.cutit.logic.model;

import java.time.LocalDateTime;

public class Promotion { //commit a caso

    private String code;
    private Integer offValue;
    private LocalDateTime expireDate;
    private Service service;

    public Promotion(String code, Integer offValue, LocalDateTime expireDate, Service service){
        setCode(code);
        setOffValue(offValue);
        setExpireDate(expireDate);
        setService(service);
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
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

}
