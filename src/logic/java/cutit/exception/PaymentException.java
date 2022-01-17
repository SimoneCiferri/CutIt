package cutit.exception;

public class PaymentException extends  Exception{

    private static final long serialVersionUID = 6L;

    public PaymentException(String message) {
        super(message);
    }

}
