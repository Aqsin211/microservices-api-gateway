package az.company.feedback.model.mapper;

import az.company.feedback.dao.entity.FeedbackEntity;
import az.company.feedback.model.dto.request.FeedbackRequestDTO;
import az.company.feedback.model.dto.response.FeedbackResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FeedbackMapper {

    @Mapping(source = "feedbackId", target = "feedbackId")
    @Mapping(source = "profileId", target = "profileId")
    @Mapping(source = "content", target = "content")
    @Mapping(source = "createdAt", target = "createdAt")
    FeedbackResponseDTO toDto(FeedbackEntity feedbackEntity);

    @Mapping(target = "feedbackId", ignore = true)
    @Mapping(source = "profileId", target = "profileId")
    @Mapping(source = "content", target = "content")
    @Mapping(target = "createdAt", ignore = true)
    FeedbackEntity toEntity(FeedbackRequestDTO feedbackRequestDTO);
}
