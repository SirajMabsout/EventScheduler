package event.managment.service;

import event.managment.modules.Event;
import event.managment.modules.Invitation;
import event.managment.modules.User;
import event.managment.repository.EventRepository;
import event.managment.repository.InvitationRepository;
import event.managment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvitationService {

    @Autowired
    private InvitationRepository invitationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    // Invite a user to an event
    public Invitation sendInvitation(Long eventId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        Invitation invitation = new Invitation();
        invitation.setUser(user);
        invitation.setEvent(event);
        invitation.setStatus(Invitation.InvitationStatus.INVITED);

        return invitationRepository.save(invitation);
    }

    // Accept an invitation
    public Invitation acceptInvitation(Long invitationId) {
        Invitation invitation = invitationRepository.findById(invitationId)
                .orElseThrow(() -> new RuntimeException("Invitation not found"));
        invitation.setStatus(Invitation.InvitationStatus.ACCEPTED);
        return invitationRepository.save(invitation);
    }

    // Decline an invitation
    public Invitation declineInvitation(Long invitationId) {
        Invitation invitation = invitationRepository.findById(invitationId)
                .orElseThrow(() -> new RuntimeException("Invitation not found"));
        invitation.setStatus(Invitation.InvitationStatus.DECLINED);
        return invitationRepository.save(invitation);
    }

    // Get all invitations for a user
    public List<Invitation> getUserInvitations(Long userId) {
        return invitationRepository.findByUserId(userId);
    }
}
