package event.managment.repository;

import event.managment.modules.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {

    // Find an invitation by its ID
    Optional<Invitation> findById(Long id);

    // Find all invitations for a specific user
    List<Invitation> findByUserId(Long userId);

    // Find all invitations for a specific event
    List<Invitation> findByEventId(Long eventId);

    // Find an invitation by user and event
    Optional<Invitation> findByUserIdAndEventId(Long userId, Long eventId);
}
