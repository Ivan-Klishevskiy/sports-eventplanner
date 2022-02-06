package by.tms.sportseventplanner.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Weather implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(updatable = false)
    private LocalDate date;

    private String city;

    private float temp;

    private int pressure;

    private int humidity;

    private float windSpeed;

    private int visibility;

    private String description;

    @PrePersist
    protected void onCreate() {
        this.date = LocalDate.now();
    }
}
