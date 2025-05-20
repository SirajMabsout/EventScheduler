package event.managment.modules;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String username;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role; // Enum for user roles (ADMIN, USER, etc.)

    @OneToMany(mappedBy = "user")
    private List<Invitation> invitations = new ArrayList<>();

    // Getters and Setters
}

