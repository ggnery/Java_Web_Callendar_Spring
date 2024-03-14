package projects.java_web_calendar_spring.exception;

public class EventNotFoundException extends Exception{
    public EventNotFoundException(String message){
        super(message);
    }
}

