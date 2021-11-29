package cutit.cutit.logic.model;

import javafx.scene.layout.BorderPane;

import java.time.LocalDateTime;

public class PromotionCode {

    private String code;
    private LocalDateTime expirationDate;
    private Boolean expired;

    //costruttore da ricontrollare bene
    public PromotionCode(String code, Boolean expired){
        setCode(code);
        setExpired(expired);
    }

    public String getCode(){
        return code;
    }

    public void setCode(String code){
        this.code = code;
    }

    //mancano i metodi per l'expirationDate

    public Boolean isExpired(){
        return expired;
    }

    public void setExpired(Boolean expired){
        this.expired = expired;
    }

}
