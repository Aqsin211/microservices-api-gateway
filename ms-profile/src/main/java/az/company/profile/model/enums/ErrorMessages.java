package az.company.profile.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessages {
    INTERNAL_SERVER_ERROR("Internal Server Error"),
    RESOURCE_NOT_FOUND("Resource Not Found"),
    FEIGN_CLIENT_ERROR("Feign client error occurred"),
    CONFLICT("Conflict"),
    VALIDATION_FAILED("Validation Failed"),
    PROFILE_WITH_NAME_EXISTS("Profile with name %s already exists"),
    PROFILE_WITH_EMAIL_EXISTS("Profile with email %s already exists"),
    PROFILE_NOT_FOUND("Profile with id %s not found"),;
    private final String message;
}
