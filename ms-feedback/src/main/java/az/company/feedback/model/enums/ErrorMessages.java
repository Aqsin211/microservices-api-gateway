package az.company.feedback.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessages {
    FEEDBACK_ALREADY_EXISTS("Feedback already exists"),
    FEEDBACK_NOT_FOUND("Feedback not found with id %s for profile id %s"),
    FEEDBACK_NOT_FOUND_WITH_FEEDBACK_ID("Feedback not found with id: %s"),
    FEEDBACK_NOT_FOUND_WITH_PROFILE_ID("Feedback not found for profile id: %s"),
    FEEDBACK_EXISTS("Feedback already exists"),
    RESOURCE_NOT_FOUND("Resource not found"),
    FEIGN_CLIENT_ERROR("Feign client error"),
    INTERNAL_SERVER_ERROR("Internal server error");
    private final String message;
}
