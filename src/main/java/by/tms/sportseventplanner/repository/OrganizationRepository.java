package by.tms.sportseventplanner.repository;

import by.tms.sportseventplanner.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization,Long> {

    boolean existsByName(String name);
}
