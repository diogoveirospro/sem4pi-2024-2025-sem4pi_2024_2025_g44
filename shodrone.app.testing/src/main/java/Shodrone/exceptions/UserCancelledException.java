package Shodrone.exceptions;

public class UserCancelledException extends RuntimeException {
    public UserCancelledException(String message) {
        super(message);
    }
}
