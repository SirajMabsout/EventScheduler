package event.managment.dto.response;


import event.managment.modules.EventStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventResponse {
    private Long id;
    private String title;
    private LocalDateTime dateTime;
    private String location;
    private String description;
    private EventStatus status;


}
