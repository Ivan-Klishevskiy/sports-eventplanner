package by.tms.sportseventplanner.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long eventId;

    private String creatorName;

    private String title;

    private String description;

    @Column(updatable = false)
    private LocalDateTime dateCreating;


    @PrePersist
    protected void onCreate() {
        this.dateCreating = LocalDateTime.now();
    }
}
