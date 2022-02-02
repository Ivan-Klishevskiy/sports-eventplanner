package by.tms.sportseventplanner.dto.comment;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class RequestCommentDto {

    private long eventId;

    private String creatorName;

    private String title;

    private String description;
}
