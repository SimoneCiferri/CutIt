package cutit.exception;

public class RecordNotFoundException extends Exception{

    private static final long serialVersionUID = 3L;
    private static final String MESSAGE = "DATA_NOT_FOUND";

    public RecordNotFoundException(Throwable cause){
        super(MESSAGE, cause);
    }

}
