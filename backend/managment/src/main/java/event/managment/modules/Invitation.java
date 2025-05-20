package event.managment.modules;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Invitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Event event;

    @Enumerated(EnumType.STRING)
    private InvitationStatus status;

    public enum InvitationStatus {
        INVITED, ACCEPTED, DECLINED
    }


}
