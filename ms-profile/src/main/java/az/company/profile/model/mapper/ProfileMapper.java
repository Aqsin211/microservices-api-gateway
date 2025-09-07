package az.company.profile.model.mapper;

import az.company.profile.dao.entity.ProfileEntity;
import az.company.profile.model.dto.request.ProfileRequestDTO;
import az.company.profile.model.dto.response.ProfileResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    @Mapping(source = "profileId", target = "profileId")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "bio", target = "bio")
    @Mapping(source = "createdAt", target = "createdAt")
    ProfileResponseDTO toDto(ProfileEntity profileEntity);

    @Mapping(target = "profileId", ignore = true)
    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "bio", target = "bio")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    ProfileEntity toEntity(ProfileRequestDTO profileRequestDTO);
}
