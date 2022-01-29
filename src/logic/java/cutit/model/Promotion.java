package cutit.model;

import java.time.LocalDate;

public class Promotion {

    private String code;
    private Integer offValue;
    private LocalDate expireDate;
    private Service service;

    public Promotion(String code, Integer offValue, LocalDate expireDate){
        setCode(code);
        setOffValue(offValue);
        setExpireDate(expireDate);
    }

    public Promotion(String code, Integer offValue, LocalDate expireDate, Service service){
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

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    public String getCode(){
        return code;
    }

    public void setCode(String code){
        this.code = code;
    }

}
