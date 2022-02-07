package by.tms.sportseventplanner.dto.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class RequestEventDto {

    @NotNull
    @NotBlank
    @Length(min = 1, max = 80)
    private String creatorOrganization;

    @NotNull
    @NotBlank
    @Length(min = 1, max = 80)
    private String sportType;

    @NotNull
    @NotBlank
    @Length(min =1, max = 2048)
    private String description;

    @NotNull
    private LocalDate date;

    @NotNull
    @NotBlank
    @Length(min = 1, max = 80)
    private String venue;
}
