package az.company.profile.model.dto.request;

import az.company.profile.exception.ValidationMessages;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@FieldDefaults(level = PRIVATE)
public class ProfileRequestDTO {
    @NotBlank(message = ValidationMessages.NAME_CANNOT_BE_BLANK)
    String name;

    @Email(message = ValidationMessages.EMAIL_FORMAT_INCORRECT)
    @NotBlank(message = ValidationMessages.EMAIL_CANNOT_BE_BLANK)
    String email;

    String bio;
}
