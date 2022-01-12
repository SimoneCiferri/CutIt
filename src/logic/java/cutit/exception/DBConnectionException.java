package cutit.exception;

public class DBConnectionException extends Exception{

    private static final long serialVersionUID = 2L;
    private static final String MESSAGE = "DB_CONNECTION_ERROR";

    public DBConnectionException(String message, Throwable cause){
        super(message, cause);
    }

}
