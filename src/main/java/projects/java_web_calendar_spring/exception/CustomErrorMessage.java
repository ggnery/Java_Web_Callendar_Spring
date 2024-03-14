package projects.java_web_calendar_spring.exception;


public class CustomErrorMessage {
    private String message;

    public CustomErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}

