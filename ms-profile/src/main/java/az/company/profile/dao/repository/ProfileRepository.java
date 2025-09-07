package az.company.profile.dao.repository;

import az.company.profile.dao.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {
    boolean existsByName(String name);

    boolean existsByEmail(String email);
}
