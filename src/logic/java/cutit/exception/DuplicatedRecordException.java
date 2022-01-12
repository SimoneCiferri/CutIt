package cutit.exception;

public class DuplicatedRecordException extends Exception{

    private static final long serialVersionUID = 4L;

    public DuplicatedRecordException(String message) {
        super(message);
    }

}
