package cutit.exception;

public class DBConnectionException extends Exception{

    private static final long serialVersionUID = 2L;

    public DBConnectionException(String message, Throwable cause){
        super(message, cause);
    }

}
