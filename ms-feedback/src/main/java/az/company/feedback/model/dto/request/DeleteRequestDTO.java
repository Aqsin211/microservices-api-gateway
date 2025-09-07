package az.company.feedback.model.dto.request;

import az.company.feedback.exception.ValidationMessages;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@FieldDefaults(level = PRIVATE)
public class DeleteRequestDTO {
    @NotNull(message = ValidationMessages.FEEDBACK_ID_REQUIRED)
    Long feedbackId;

    @NotNull(message = ValidationMessages.PROFILE_ID_REQUIRED)
    Long profileId;
}
