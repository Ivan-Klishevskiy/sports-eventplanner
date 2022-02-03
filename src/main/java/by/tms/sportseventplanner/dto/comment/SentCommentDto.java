package by.tms.sportseventplanner.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class SentCommentDto {

    private long id;

    private long eventId;

    private String creatorName;

    private String title;

    private String description;

    private LocalDateTime dateCreating;
}
