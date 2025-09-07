package az.company.feedback.service;

import az.company.feedback.client.ProfileClient;
import az.company.feedback.dao.entity.FeedbackEntity;
import az.company.feedback.dao.repository.FeedbackRepository;
import az.company.feedback.exception.FeedbackExistsException;
import az.company.feedback.exception.NotFoundException;
import az.company.feedback.model.dto.request.DeleteRequestDTO;
import az.company.feedback.model.dto.request.FeedbackRequestDTO;
import az.company.feedback.model.dto.request.UpdateRequestDTO;
import az.company.feedback.model.dto.response.FeedbackResponseDTO;
import az.company.feedback.model.enums.ErrorMessages;
import az.company.feedback.model.mapper.FeedbackMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final FeedbackMapper feedbackMapper;
    private final ProfileClient profileClient;

    public FeedbackService(FeedbackRepository feedbackRepository,
                           FeedbackMapper feedbackMapper,
                           ProfileClient profileClient) {
        this.feedbackRepository = feedbackRepository;
        this.feedbackMapper = feedbackMapper;
        this.profileClient = profileClient;
    }

    public Long addFeedback(FeedbackRequestDTO feedbackRequest) {
        profileClient.validateProfile(feedbackRequest.getProfileId());

        if (feedbackRepository.existsByProfileIdAndContent(feedbackRequest.getProfileId(),
                feedbackRequest.getContent())) {
            throw new FeedbackExistsException(ErrorMessages.FEEDBACK_ALREADY_EXISTS.getMessage());
        }

        return feedbackRepository.save(feedbackMapper.toEntity(feedbackRequest)).getFeedbackId();
    }

    public List<FeedbackResponseDTO> getAllFeedbackByProfileId(Long profileId) {
        return feedbackRepository.findAllByProfileId(profileId)
                .stream()
                .map(feedbackMapper::toDto)
                .collect(Collectors.toList());
    }

    public FeedbackResponseDTO getFeedbackById(Long feedbackId) {
        return feedbackRepository.findById(feedbackId)
                .map(feedbackMapper::toDto)
                .orElseThrow(() -> new NotFoundException(
                                format(
                                        ErrorMessages.FEEDBACK_NOT_FOUND.getMessage(), feedbackId
                                )
                        )
                );
    }

    public List<FeedbackResponseDTO> getAllFeedbacks() {
        return feedbackRepository.findAll()
                .stream()
                .map(feedbackMapper::toDto)
                .collect(Collectors.toList());
    }

    public void deleteFeedback(DeleteRequestDTO deleteRequest) {
        boolean exists = feedbackRepository.existsByFeedbackIdAndProfileId(
                deleteRequest.getFeedbackId(), deleteRequest.getProfileId());
        if (!exists) {
            throw new NotFoundException(
                    format(
                            ErrorMessages.FEEDBACK_NOT_FOUND.getMessage(),
                            deleteRequest.getFeedbackId(),
                            deleteRequest.getProfileId())

            );
        }
        feedbackRepository.deleteById(deleteRequest.getFeedbackId());
    }

    public void deleteAllFeedbacksByProfileId(Long profileId) {
        List<Long> feedbackIds = feedbackRepository.findAllByProfileId(profileId)
                .stream()
                .map(FeedbackEntity::getFeedbackId)
                .toList();

        feedbackRepository.deleteAllById(feedbackIds);
    }

    public void updateFeedback(UpdateRequestDTO updateRequest) {
        FeedbackEntity feedbackEntity = feedbackRepository
                .findByFeedbackIdAndProfileId(updateRequest.getFeedbackId(), updateRequest.getProfileId())
                .orElseThrow(
                        () -> new NotFoundException(
                                format(
                                        ErrorMessages.FEEDBACK_NOT_FOUND.getMessage(),
                                        updateRequest.getFeedbackId(),
                                        updateRequest.getProfileId()
                                )
                        )
                );
        feedbackEntity.setContent(updateRequest.getContent());
        feedbackRepository.save(feedbackEntity);
    }
}
