package by.tms.sportseventplanner.dto.organization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class OrganizationDto {

    @NotBlank
    @Length(min = 1, max = 80)
    private String name;

    @NotNull
    @NotBlank
    @Length(min = 3, max = 255)
    private String password;

    @NotNull
    @NotBlank
    @Length(min = 1, max = 2048)
    private String description;

    @NotNull
    @NotBlank
    private String phoneNumber;
}
