package az.company.profile.client.decoder;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum JsonNodeFieldName {
    MESSAGE("message");
    private final String value;
}
