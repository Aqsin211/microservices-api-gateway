package az.company.profile.controller;

import az.company.profile.model.dto.request.ProfileRequestDTO;
import az.company.profile.model.dto.response.ProfileResponseDTO;
import az.company.profile.model.enums.CrudMessages;
import az.company.profile.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/profiles")
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public String create(@Valid @RequestBody ProfileRequestDTO profileRequestDTO) {
        Long id = profileService.createProfile(profileRequestDTO);
        return String.format(CrudMessages.PROFILE_CREATED.getMessage(), id);
    }

    @GetMapping
    public ResponseEntity<List<ProfileResponseDTO>> getAll() {
        return ResponseEntity.ok(profileService.getAllProfiles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponseDTO> getProfile(@PathVariable Long id) {
        return ResponseEntity.ok(profileService.getProfile(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id,
                                         @Valid @RequestBody ProfileRequestDTO profileRequestDTO) {
        profileService.updateProfile(id, profileRequestDTO);
        return ResponseEntity.ok(CrudMessages.PROFILE_UPDATED.getMessage());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        profileService.deleteProfile(id);
        return ResponseEntity.ok(CrudMessages.PROFILE_DELETED.getMessage());
    }

    @GetMapping("/validate/{id}")
    public Boolean validateProfile(@PathVariable Long id) {
        return profileService.profileIsValid(id);
    }
}
