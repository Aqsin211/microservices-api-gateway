package az.company.profile.exception;

public class AlreadyAtUseException extends RuntimeException {
    public AlreadyAtUseException(String message) {
        super(message);
    }
}
