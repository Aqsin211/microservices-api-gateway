package az.company.profile.service;

import az.company.profile.client.FeedbackClient;
import az.company.profile.dao.entity.ProfileEntity;
import az.company.profile.dao.repository.ProfileRepository;
import az.company.profile.exception.AlreadyAtUseException;
import az.company.profile.exception.NotFoundException;
import az.company.profile.model.dto.request.ProfileRequestDTO;
import az.company.profile.model.dto.response.ProfileResponseDTO;
import az.company.profile.model.enums.ErrorMessages;
import az.company.profile.model.mapper.ProfileMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;
    private final FeedbackClient feedbackClient;

    public ProfileService(ProfileRepository profileRepository, ProfileMapper profileMapper,
                          FeedbackClient feedbackClient) {
        this.profileRepository = profileRepository;
        this.profileMapper = profileMapper;
        this.feedbackClient = feedbackClient;
    }

    public Long createProfile(ProfileRequestDTO profileRequest) {
        if (profileRepository.existsByName(profileRequest.getName())) {
            throw new AlreadyAtUseException(
                    format(
                            ErrorMessages.PROFILE_WITH_NAME_EXISTS.getMessage(),
                            profileRequest.getName()
                    )
            );
        }

        if (profileRepository.existsByEmail(profileRequest.getEmail())) {
            throw new AlreadyAtUseException(
                    format(
                            ErrorMessages.PROFILE_WITH_EMAIL_EXISTS.getMessage(),
                            profileRequest.getEmail()
                    )
            );
        }

        return profileRepository.save(profileMapper.toEntity(profileRequest)).getProfileId();
    }

    public List<ProfileResponseDTO> getAllProfiles() {
        return profileRepository
                .findAll()
                .stream()
                .map(profileMapper::toDto)
                .collect(Collectors.toList());
    }

    public ProfileResponseDTO getProfile(Long id) {
        return profileRepository.findById(id)
                .map(profileMapper::toDto)
                .orElseThrow(() -> new NotFoundException(
                                format(
                                        ErrorMessages.PROFILE_NOT_FOUND.getMessage(), id
                                )
                        )
                );
    }

    public void updateProfile(Long id, ProfileRequestDTO profileRequest) {
        ProfileEntity profileEntity = profileRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                                format(
                                        ErrorMessages.PROFILE_NOT_FOUND.getMessage(), id
                                )
                        )
                );

        if (!profileEntity.getName().equals(profileRequest.getName())
                && profileRepository.existsByName(profileRequest.getName())) {
            throw new AlreadyAtUseException(
                    format(
                            ErrorMessages.PROFILE_WITH_NAME_EXISTS.getMessage(),
                            profileRequest.getName()
                    )
            );
        }

        if (!profileEntity.getEmail().equals(profileRequest.getEmail())
                && profileRepository.existsByEmail(profileRequest.getEmail())) {
            throw new AlreadyAtUseException(
                    format(
                            ErrorMessages.PROFILE_WITH_EMAIL_EXISTS.getMessage(),
                            profileRequest.getEmail()
                    )
            );
        }

        profileEntity.setName(profileRequest.getName());
        profileEntity.setEmail(profileRequest.getEmail());
        profileEntity.setBio(profileRequest.getBio());

        profileRepository.save(profileEntity);
    }

    public void deleteProfile(Long id) {

        if (profileRepository.existsById(id)) {
            feedbackClient.deleteByProfileId(id);
            profileRepository.deleteById(id);
        } else {
            throw new NotFoundException(
                    format(
                            ErrorMessages.PROFILE_NOT_FOUND.getMessage(), id
                    )
            );
        }

    }

    public Boolean profileIsValid(Long id) {
        return profileRepository.existsById(id);
    }

}
