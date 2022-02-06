package by.tms.sportseventplanner.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String creatorOrganization;

    private String sportType;

    private String description;

    private LocalDate date;

    @ManyToOne(cascade = CascadeType.DETACH)
    private Weather weather;

    private String venue;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> participants;
}
