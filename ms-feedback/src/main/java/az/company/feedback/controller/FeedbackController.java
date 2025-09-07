package az.company.feedback.controller;

import az.company.feedback.model.dto.request.DeleteRequestDTO;
import az.company.feedback.model.dto.request.FeedbackRequestDTO;
import az.company.feedback.model.dto.request.UpdateRequestDTO;
import az.company.feedback.model.dto.response.FeedbackResponseDTO;
import az.company.feedback.model.enums.CrudMessages;
import az.company.feedback.service.FeedbackService;
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

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public String create(@Valid @RequestBody FeedbackRequestDTO feedbackRequestDTO) {
        Long id = feedbackService.addFeedback(feedbackRequestDTO);
        return format(CrudMessages.FEEDBACK_CREATED.getMessage(), id);
    }

    @GetMapping
    public ResponseEntity<List<FeedbackResponseDTO>> getAll() {
        return ResponseEntity.ok(feedbackService.getAllFeedbacks());
    }

    @GetMapping("/{feedbackId}")
    public ResponseEntity<FeedbackResponseDTO> getById(@PathVariable Long feedbackId) {
        return ResponseEntity.ok(feedbackService.getFeedbackById(feedbackId));
    }

    @GetMapping("/profile/{profileId}")
    public ResponseEntity<List<FeedbackResponseDTO>> getAllByProfileId(@PathVariable Long profileId) {
        return ResponseEntity.ok(feedbackService.getAllFeedbackByProfileId(profileId));
    }

    @PutMapping
    public ResponseEntity<String> update(@Valid @RequestBody UpdateRequestDTO updateRequest) {
        feedbackService.updateFeedback(updateRequest);
        return ResponseEntity.ok(CrudMessages.FEEDBACK_UPDATED.getMessage());
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@Valid @RequestBody DeleteRequestDTO deleteRequest) {
        feedbackService.deleteFeedback(deleteRequest);
        return ResponseEntity.ok(CrudMessages.FEEDBACK_DELETED.getMessage());
    }

    @DeleteMapping("/profile/{profileId}")
    public ResponseEntity<String> deleteByProfileId(@PathVariable Long profileId) {
        feedbackService.deleteAllFeedbacksByProfileId(profileId);
        return ResponseEntity.ok(CrudMessages.FEEDBACK_DELETED.getMessage());
    }
}
