package by.tms.sportseventplanner.controller;

import by.tms.sportseventplanner.dto.organization.OrganizationDto;
import by.tms.sportseventplanner.entity.Organization;
import by.tms.sportseventplanner.service.OrganizationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/organization")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private ModelMapper mapper;

    @PostMapping("/reg")
    public ResponseEntity<?> registration(@RequestBody OrganizationDto organizationDto) {
        Organization saved = organizationService.save(mapper.map(organizationDto,Organization.class));
        return new ResponseEntity<>(mapper.map(saved, OrganizationDto.class), HttpStatus.OK);
    }
}
