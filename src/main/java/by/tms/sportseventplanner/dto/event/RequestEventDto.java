package by.tms.sportseventplanner.dto.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class RequestEventDto {

    private String creatorOrganization;

    private String sportType;

    private String description;

    private LocalDate date;

    private String venue;
}
