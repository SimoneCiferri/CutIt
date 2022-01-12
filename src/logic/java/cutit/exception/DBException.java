package cutit.exception;

public class DBException extends Exception{

    private static final long serialVersionUID = 2L;

    public DBException(String message, Throwable cause){
        super(message, cause);
    }

}
