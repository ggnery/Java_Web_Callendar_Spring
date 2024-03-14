package projects.java_web_calendar_spring.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
@Entity
@Table(name = "event")
@Service

public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_seq")
    @SequenceGenerator(name = "event_seq", sequenceName = "id", allocationSize = 1)
    @Column(name = "id")
    private long id;
    @NotBlank
    @NotNull
    @Column(name = "event")
    private String event;
    @NotNull
    @Column(name = "date")
    private LocalDate date;

    public Event() {
    }

    public Event(String event, LocalDate date) {
        this.event = event;
        this.date = date;
    }


    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}


