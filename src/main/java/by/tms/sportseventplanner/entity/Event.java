package by.tms.sportseventplanner.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long creatorOrganization;

    private String sportType;

    private String description;

    @Column(updatable = false)
    private LocalDateTime date;

    private long weatherId;

    private String venue;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> participants;
}
