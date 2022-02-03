package by.tms.sportseventplanner.dto.organization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class OrganizationDto {

    private String name;

    private String password;

    private String description;

    private String phoneNumber;
}
