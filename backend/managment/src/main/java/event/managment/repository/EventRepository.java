package event.managment.repository;

import event.managment.modules.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByTitleContainingIgnoreCase(String title);
    List<Event> findByDateTimeBetween(LocalDateTime start, LocalDateTime end);
    List<Event> findByLocationContainingIgnoreCase(String location);
}
