package by.tms.sportseventplanner.dto.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class SentEventDto {

    private long id;

    private String creatorOrganization;

    private String sportType;

    private String description;

    private LocalDate date;

    private long weatherId;

    private String venue;

}
