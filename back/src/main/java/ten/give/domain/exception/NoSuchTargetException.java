package ten.give.domain.exception;

public class NoSuchTargetException extends RuntimeException{
    public NoSuchTargetException() {
    }

    public NoSuchTargetException(String message) {
        super(message);
    }

    public NoSuchTargetException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchTargetException(Throwable cause) {
        super(cause);
    }

    public NoSuchTargetException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
