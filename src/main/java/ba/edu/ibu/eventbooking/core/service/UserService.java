package ba.edu.ibu.eventbooking.core.service;


import ba.edu.ibu.eventbooking.core.exceptions.repository.ResourceNotFoundException;
import ba.edu.ibu.eventbooking.core.model.User;
import ba.edu.ibu.eventbooking.core.repository.UserRepository;
import ba.edu.ibu.eventbooking.rest.dto.UserDTO;
import ba.edu.ibu.eventbooking.rest.dto.UserRequestDTO;
import ba.edu.ibu.eventbooking.core.api.mailsender.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final MailSender sendgridSender;

    @Autowired
    public UserService(UserRepository userRepository, @Qualifier("mailgunSender") MailSender sendgridSender) {
        this.userRepository = userRepository;
        this.sendgridSender = sendgridSender;
    }

    public List<UserDTO> getUsers() {
        List<User> users = userRepository.findAll();

        return users
                .stream()
                .map(UserDTO::new)
                .collect(toList());
    }

    public UserDTO getUserById(String id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("The user with the given ID does not exist.");
        }
        return new UserDTO(user.get());
    }

    public UserDTO addUser(UserRequestDTO payload) {
        User user = userRepository.save(payload.toEntity());
        return new UserDTO(user);
    }

    public UserDTO updateUser(String id, UserRequestDTO payload) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("The user with the given ID does not exist.");
        }
        User updatedUser = payload.toEntity();
        updatedUser.setId(user.get().getId());
        updatedUser = userRepository.save(updatedUser);
        return new UserDTO(updatedUser);
    }

    public void deleteUser(String id) {
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(userRepository::delete);
    }

    public UserDTO filterByEmail(String email) {
        Optional<User> user = userRepository.findFirstByEmailLike(email);
        return user.map(UserDTO::new).orElse(null);
    }
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByUsernameOrEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));
    }
    public void sendEmailToUser(String userId, String message) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            List<User> userList = Collections.singletonList(user.get());
            sendgridSender.send(userList, message);
        }
    }

}