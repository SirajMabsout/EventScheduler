package event.managment.controller;

import event.managment.modules.Invitation;
import event.managment.service.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invitations")
public class InvitationController {

    @Autowired
    private InvitationService invitationService;

    // Invite a user to an event
    @PostMapping("/{eventId}/invite/{userId}")
    public ResponseEntity<Invitation> inviteUser(@PathVariable Long eventId, @PathVariable Long userId) {
        Invitation invitation = invitationService.sendInvitation(eventId, userId);
        return new ResponseEntity<>(invitation, HttpStatus.CREATED);
    }

    // Accept an invitation
    @PutMapping("/accept/{invitationId}")
    public ResponseEntity<Invitation> acceptInvitation(@PathVariable Long invitationId) {
        Invitation invitation = invitationService.acceptInvitation(invitationId);
        return ResponseEntity.ok(invitation);
    }

    // Decline an invitation
    @PutMapping("/decline/{invitationId}")
    public ResponseEntity<Invitation> declineInvitation(@PathVariable Long invitationId) {
        Invitation invitation = invitationService.declineInvitation(invitationId);
        return ResponseEntity.ok(invitation);
    }

    // Get all invitations for a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Invitation>> getUserInvitations(@PathVariable Long userId) {
        List<Invitation> invitations = invitationService.getUserInvitations(userId);
        return ResponseEntity.ok(invitations);
    }
}
