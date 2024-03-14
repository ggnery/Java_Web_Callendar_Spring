package projects.java_web_calendar_spring.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import projects.java_web_calendar_spring.entities.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}

