package by.tms.sportseventplanner.service;

import by.tms.sportseventplanner.entity.Organization;
import by.tms.sportseventplanner.repository.CommentRepository;
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

    @Autowired
    private CommentRepository commentRepository;

    public Organization save(Organization organization) {
        if (organizationRepository.existsByName(organization.getName())) {
            throw new RuntimeException(String.format("Organization %s already exist!", organization.getName()));
        } else {
            return organizationRepository.save(organization);
        }
    }

    @Transactional
    public Organization delete(String name) {
        Organization organization = organizationRepository
                .findByName(name)
                .orElseThrow(() -> new RuntimeException(String.format("Organization %s is not exist!", name)));
        organizationRepository.deleteByName(name);
        eventRepository.deleteAllByCreatorOrganization(name);
        commentRepository.deleteAllByCreatorName(name);
        return organization;
    }

    public Organization update(Organization updated, String name) {
        Organization current = organizationRepository
                .findByName(name)
                .orElseThrow(() -> new RuntimeException(String.format("Organization %s is not exist!", name)));
        updated.setId(current.getId());
        //TODO добавить изменение данных об организации в ее постах и коментах
        return organizationRepository.saveAndFlush(updated);
    }

}
