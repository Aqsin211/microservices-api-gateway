package az.company.feedback.model.dto.request;

import az.company.feedback.exception.ValidationMessages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@FieldDefaults(level = PRIVATE)
public class UpdateRequestDTO {
    @NotNull(message = ValidationMessages.FEEDBACK_ID_REQUIRED)
    Long feedbackId;

    @NotNull(message = ValidationMessages.PROFILE_ID_REQUIRED)
    Long profileId;

    @NotBlank(message = ValidationMessages.CONTENT_CANNOT_BE_BLANK)
    @Size(min = 5, max = 200, message = ValidationMessages.CONTENT_MUST_BE_BETWEEN)
    String content;
}
