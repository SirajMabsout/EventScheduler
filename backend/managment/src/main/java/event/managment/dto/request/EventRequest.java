package event.managment.dto.request;

import event.managment.modules.EventStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {
    private String title;
    private LocalDateTime dateTime;
    private String location;
    private String description;
    private EventStatus status;


}

