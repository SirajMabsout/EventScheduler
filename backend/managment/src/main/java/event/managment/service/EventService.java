package event.managment.service;

import event.managment.dto.request.EventRequest;
import event.managment.dto.response.EventResponse;
import event.managment.modules.Event;
import event.managment.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    // Create
    public EventResponse createEvent(EventRequest request) {
        Event event = mapToEntity(request);
        Event saved = eventRepository.save(event);
        return mapToResponse(saved);
    }

    // Update
    public Optional<EventResponse> updateEvent(Long id, EventRequest request) {
        return eventRepository.findById(id).map(event -> {
            event.setTitle(request.getTitle());
            event.setDateTime(request.getDateTime());
            event.setLocation(request.getLocation());
            event.setDescription(request.getDescription());
            event.setStatus(request.getStatus());
            return mapToResponse(eventRepository.save(event));
        });
    }

    // Delete
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    // Get all
    public List<EventResponse> getAllEvents() {
        return eventRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Get by ID
    public Optional<EventResponse> getEventById(Long id) {
        return eventRepository.findById(id).map(this::mapToResponse);
    }

    // Search
    public List<EventResponse> searchEvents(String title, String location, LocalDateTime start, LocalDateTime end) {
        List<Event> results;

        if (title != null && !title.isEmpty()) {
            results = eventRepository.findByTitleContainingIgnoreCase(title);
        } else if (location != null && !location.isEmpty()) {
            results = eventRepository.findByLocationContainingIgnoreCase(location);
        } else if (start != null && end != null) {
            results = eventRepository.findByDateTimeBetween(start, end);
        } else {
            results = eventRepository.findAll();
        }

        return results.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    // === Mappers ===

    private Event mapToEntity(EventRequest request) {
        Event event = new Event();
        event.setTitle(request.getTitle());
        event.setDateTime(request.getDateTime());
        event.setLocation(request.getLocation());
        event.setDescription(request.getDescription());
        event.setStatus(request.getStatus());
        return event;
    }

    private EventResponse mapToResponse(Event event) {
        EventResponse response = new EventResponse();
        response.setId(event.getId());
        response.setTitle(event.getTitle());
        response.setDateTime(event.getDateTime());
        response.setLocation(event.getLocation());
        response.setDescription(event.getDescription());
        response.setStatus(event.getStatus());
        return response;
    }
}
