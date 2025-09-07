package az.company.feedback.dao.repository;

import az.company.feedback.dao.entity.FeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeedbackRepository extends JpaRepository<FeedbackEntity, Long> {
    boolean existsByProfileIdAndContent(Long profileId, String content);

    List<FeedbackEntity> findAllByProfileId(Long profileId);

    boolean existsByFeedbackIdAndProfileId(Long feedbackId, Long profileId);

    Optional<FeedbackEntity> findByFeedbackIdAndProfileId(Long feedbackId, Long profileId);
}
