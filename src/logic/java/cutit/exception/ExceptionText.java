package cutit.exception;

public class ExceptionText {

    private ExceptionText(){}

    private static final String CONNECTION_ERROR_TITLE = "Connection error";
    private static final String WARNING_TITLE = "Warning";
    private static final String ERROR_TITLE = "Error";
    private static final String INFORMATION_TITLE = "Information";
    private static final String CONNECTION_ERROR_MESSAGE = "Please check your internet connection. If problem persists try to restart the application.";
    private static final String SQL_ERROR_MESSAGE = "Please check your internet connection. If problem persists contact us at cutitapp@support.com.";
    private static final String FILE_NOT_FOUND_ERROR_MESSAGE = "An error occurred handling some files. Please try again.";

    public static String getConnectionErrorTitle() {
        return CONNECTION_ERROR_TITLE;
    }

    public static String getWarningTitle() {
        return WARNING_TITLE;
    }

    public static String getErrorTitle() {
        return ERROR_TITLE;
    }

    public static String getInformationTitle() {
        return INFORMATION_TITLE;
    }

    public static String getConnectionErrorMessage() {
        return CONNECTION_ERROR_MESSAGE;
    }

    public static String getSqlErrorMessage() {
        return SQL_ERROR_MESSAGE;
    }

    public static String getFileNotFoundErrorMessage() {
        return FILE_NOT_FOUND_ERROR_MESSAGE;
    }

}
