package event.managment.dto.response;

import event.managment.modules.Role;
import lombok.Data;


@Data
public class UserResponse {

    private Long id;
    private String username;
    private String email;
    private Role role;

    // Getters and Setters
}
