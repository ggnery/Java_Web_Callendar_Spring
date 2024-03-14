package projects.java_web_calendar_spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<CustomErrorMessage> handleEventNotFoundException(EventNotFoundException e){
        CustomErrorMessage body = new CustomErrorMessage(e.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }


}
