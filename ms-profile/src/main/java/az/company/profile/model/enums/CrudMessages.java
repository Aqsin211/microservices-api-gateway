package az.company.profile.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CrudMessages {
    PROFILE_CREATED("Profile created with id: %s"),
    PROFILE_UPDATED("Profile updated"),
    PROFILE_DELETED("Profile deleted"),;
    private final String message;
}
