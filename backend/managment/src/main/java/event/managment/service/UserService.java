package event.managment.service;

import event.managment.dto.request.UserLoginRequest;
import event.managment.dto.request.UserRequest;
import event.managment.dto.response.UserResponse;
import event.managment.modules.Role;
import event.managment.modules.User;
import event.managment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Create new user
    public UserResponse createUser(UserRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());  // You should hash this before saving
        user.setRole(Role.USER);  // Default role can be USER
        user = userRepository.save(user);
        return mapToResponse(user);
    }

    // Get user by ID
    public Optional<UserResponse> getUserById(Long id) {
        return userRepository.findById(id).map(this::mapToResponse);
    }

    public Optional<User> loginUser(UserLoginRequest loginRequest) {
        return userRepository.findByEmailAndPassword(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );
    }

    // Search users by username
    public List<UserResponse> searchUsers(String username) {
        return userRepository.findByUsernameContainingIgnoreCase(username)
                .stream().map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Map user entity to response DTO
    private UserResponse mapToResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());
        return response;
    }
}
