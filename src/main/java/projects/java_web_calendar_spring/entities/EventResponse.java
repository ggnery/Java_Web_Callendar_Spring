package projects.java_web_calendar_spring.entities;

import java.time.LocalDate;

public class EventResponse{
    private String event;
    private LocalDate date;
    private String message;

    public EventResponse(){
    }

    public EventResponse(String event, LocalDate date, String message) {
        this.message = message;
        this.date = date;
        this.event = event;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

