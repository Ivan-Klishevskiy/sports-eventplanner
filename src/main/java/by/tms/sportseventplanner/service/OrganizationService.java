package by.tms.sportseventplanner.service;

import by.tms.sportseventplanner.entity.Organization;
import by.tms.sportseventplanner.repository.EventRepository;
import by.tms.sportseventplanner.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private EventRepository eventRepository;

    public Organization save(Organization organization) {
        if (organizationRepository.existsByName(organization.getName())) {
            throw new RuntimeException(String.format("Organization {} already exist! %s", organization.getName()));
        } else {
            return organizationRepository.save(organization);
        }
    }

    @Transactional
    public Organization delete(String name) {
        Organization organization = organizationRepository
                .findByName(name)
                .orElseThrow(() -> new RuntimeException(String.format("Organization {} is not exist! %s", name)));
        organizationRepository.deleteByName(name);
        eventRepository.deleteByCreatorOrganization(name);
        return organization;
    }

    public Organization update(Organization updated, String name) {
        Organization current = organizationRepository
                .findByName(name)
                .orElseThrow(() -> new RuntimeException(String.format("Organization {} is not exist! %s", name)));
        updated.setId(current.getId());
        //TODO добавить изменение данных об организации в ее постах и коментах
        return organizationRepository.saveAndFlush(updated);
    }

}
