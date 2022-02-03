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

    @JsonProperty("name")
    private String city;

    @JsonProperty("temp")
    private float temp;

    @JsonProperty("pressure")
    private int pressure;

    @JsonProperty("humidity")
    private int humidity;

    @JsonProperty("windSpeed")
    private float windSpeed;

    @JsonProperty("visibility")
    private int visibility;

    @JsonProperty("description")
    private String description;

    @PrePersist
    protected void onCreate() {
        this.date = LocalDate.now();
    }
}
