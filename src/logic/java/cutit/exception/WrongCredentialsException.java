package cutit.exception;

public class WrongCredentialsException extends Exception{

    private static final long serialVersionUID = 1L;
    private static final String MESSAGE = "WRONG_CREDENTIALS";
    private final Throwable cause = new Throwable("Login Failed. Check your credentials.");

    public WrongCredentialsException(){
        super(MESSAGE);
        this.initCause(cause);
    }

}
