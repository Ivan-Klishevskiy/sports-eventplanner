package by.tms.sportseventplanner.service;

import by.tms.sportseventplanner.entity.Organization;
import by.tms.sportseventplanner.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService {

    @Autowired
    OrganizationRepository organizationRepository;

    public Organization save(Organization organization){
        if(organizationRepository.existsByName(organization.getName())){
            throw new RuntimeException(String.format("Organization {} already exist! %s", organization.getName()));
        }else{
            return organizationRepository.save(organization);
        }
    }
}
