package cutit.exception;

public class WrongInputDataException extends Exception{

    private static final long serialVersionUID = 5L;

    public WrongInputDataException(String message) {
        super(message);
    }

}
