package projects.java_web_calendar_spring.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import projects.java_web_calendar_spring.entities.Event;
import projects.java_web_calendar_spring.entities.EventResponse;
import projects.java_web_calendar_spring.exception.EventNotFoundException;
import projects.java_web_calendar_spring.persistence.EventRepository;


import java.time.LocalDate;
import java.util.*;


@RestController
public class Controller {
    @Autowired
    EventRepository eventRepository;

    @GetMapping("/event/today")
    public ResponseEntity<List<Event>> getTodayEvents(){
        List<Event> events = eventRepository.findAll();

        List<Event> answer  = new ArrayList<>();
        for(Event event: events){
            if(event.getDate().isEqual(LocalDate.now())){
                answer.add(event);
            }
        }

        if (answer.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList());
        }
        return ResponseEntity.ok(answer);

    }

    @GetMapping("/event")
    public ResponseEntity<List<Event>> getAllEvents(){

        List<Event> events = eventRepository.findAll();
        if (events.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return  ResponseEntity.ok(events);
    }

    @PostMapping("/event")
    public ResponseEntity<?> saveEvent(@Valid @RequestBody Event event, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().build();
        }

        eventRepository.saveAndFlush(event);
        EventResponse response  = new EventResponse(event.getEvent(),event.getDate(), "The event has been added!");

        return  ResponseEntity.ok(response);
    }

    @DeleteMapping("event/delete")
    public ResponseEntity<String> deleteAllEvents(){
        eventRepository.deleteAll();
        return ResponseEntity.ok("All events have been deleted");
    }


    @GetMapping("/event/{id}")
    public ResponseEntity<?> getEventById(@PathVariable("id") Long id) throws EventNotFoundException {
        Event event = eventRepository.findById(id).orElse(null);
        if(event == null){
            throw new EventNotFoundException("The event doesn't exist!");
        }

        return new ResponseEntity<>(event, HttpStatus.OK);

    }



    @DeleteMapping("/event/{id}")
    public ResponseEntity<?> deleteEventById(@PathVariable("id") Long id) throws EventNotFoundException {
        Event event = eventRepository.findById(id).orElse(null);
        if(event == null){
            throw new EventNotFoundException("The event doesn't exist!");
        }
        eventRepository.deleteById(id);
        EventResponse response = new EventResponse(event.getEvent(), event.getDate(),"The event has been deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);


    }

    @GetMapping(path = "/event", params = {"start_time", "end_time"})
    public ResponseEntity<?> getEventBetweenDates(@RequestParam LocalDate start_time, @RequestParam LocalDate end_time ){
        List<Event> events = eventRepository.findAll();
        List<Event> answer = new ArrayList<>();

        for(Event event: events){
            if(event.getDate().isAfter(start_time) && event.getDate().isBefore(end_time)){
                answer.add(event);
            }
        }

        if(answer.isEmpty()){
            return new ResponseEntity<>(events, HttpStatus.OK);
        }
        return new ResponseEntity<>(answer, HttpStatus.OK);

    }

}

