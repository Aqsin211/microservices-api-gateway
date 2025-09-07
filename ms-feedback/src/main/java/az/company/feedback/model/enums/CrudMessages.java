package az.company.feedback.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CrudMessages {
    FEEDBACK_UPDATED("Feedback updated"),
    FEEDBACK_DELETED("Feedback deleted"),
    FEEDBACK_CREATED("Feedback created with id: %s");
    private final String message;
}
