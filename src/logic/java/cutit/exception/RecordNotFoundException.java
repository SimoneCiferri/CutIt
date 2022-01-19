package cutit.exception;

public class RecordNotFoundException extends Exception{

    private static final long serialVersionUID = 3L;

    public RecordNotFoundException(String message){
        super(message);
    }

    public RecordNotFoundException(String message, Throwable cause){
        super(message, cause);
    }

}
