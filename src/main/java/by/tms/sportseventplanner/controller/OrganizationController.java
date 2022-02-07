package by.tms.sportseventplanner.controller;

import by.tms.sportseventplanner.dto.organization.OrganizationDto;
import by.tms.sportseventplanner.entity.Organization;
import by.tms.sportseventplanner.service.OrganizationService;
import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/organization")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private ModelMapper mapper;

    @PostMapping("/reg")
    public ResponseEntity<?> registration(@Valid @RequestBody OrganizationDto organizationDto) {
        Organization saved = organizationService.save(mapper.map(organizationDto, Organization.class));
        return new ResponseEntity<>(mapper.map(saved, OrganizationDto.class), HttpStatus.CREATED);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<?> delete(@PathVariable @Length(min = 1, max = 255) String name) {
        Organization deleted = organizationService.delete(name);
        return new ResponseEntity<>(mapper.map(deleted, OrganizationDto.class), HttpStatus.OK);
    }

    @PutMapping("/{name}")
    public ResponseEntity<?> update(@PathVariable String name, @Valid @RequestBody OrganizationDto organizationDto) {
        Organization updated = organizationService.update(mapper.map(organizationDto, Organization.class), name);
        return new ResponseEntity<>(mapper.map(updated, OrganizationDto.class), HttpStatus.OK);
    }
}
