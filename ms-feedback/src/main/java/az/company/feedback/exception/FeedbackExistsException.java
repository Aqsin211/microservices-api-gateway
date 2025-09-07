package az.company.feedback.exception;

public class FeedbackExistsException extends RuntimeException {
    public FeedbackExistsException(String message) {
        super(message);
    }
}
