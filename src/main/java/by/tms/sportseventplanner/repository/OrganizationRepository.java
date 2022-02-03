package by.tms.sportseventplanner.repository;

import by.tms.sportseventplanner.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization,Long> {

    boolean existsByName(String name);

    void deleteByName(String name);

    Optional<Organization> findByName(String name);
}
