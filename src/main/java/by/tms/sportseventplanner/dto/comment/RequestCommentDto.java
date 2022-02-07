package by.tms.sportseventplanner.dto.comment;


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
public class RequestCommentDto {

    @NotNull
    private long eventId;

    @NotNull
    @NotBlank
    @Length(min = 1, max = 80)
    private String creatorName;

    @NotNull
    @NotBlank
    @Length(min = 1, max = 80)
    private String title;

    @NotNull
    @NotBlank
    @Length(min = 1, max = 2048)
    private String description;
}
