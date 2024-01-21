package ba.edu.ibu.eventbooking.core.service;

import ba.edu.ibu.eventbooking.core.api.mailsender.MailSender;
import ba.edu.ibu.eventbooking.core.exceptions.repository.ResourceNotFoundException;
import ba.edu.ibu.eventbooking.core.model.User;
import ba.edu.ibu.eventbooking.core.repository.UserRepository;
import ba.edu.ibu.eventbooking.rest.dto.UserDTO;
import ba.edu.ibu.eventbooking.rest.dto.UserRequestDTO;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@AutoConfigureMockMvc
@SpringBootTest
public class UserServiceTest {
    @MockBean
    private UserRepository userRepository;

    @MockBean
    private MailSender sendgridSender;

    @Autowired
    private UserService userService;

    @Test
    void shouldGetUsers() {
        when(userRepository.findAll()).thenReturn(Collections.emptyList());

        assertEquals(Collections.emptyList(), userService.getUsers());

        verify(userRepository, times(1)).findAll();
    }

    @Test
    void shouldGetUserById() {
        String userId = "1";
        User user = new User();
        user.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        UserDTO result = userService.getUserById(userId);

        assertEquals(userId, result.getId());

        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void shouldThrowExceptionWhenUserNotFoundById() {
        String userId = "1";

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.getUserById(userId));

        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void shouldAddUser() {
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setUsername("test");

        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        UserDTO result = userService.addUser(userRequestDTO);

        assertEquals(userRequestDTO.getUsername(), result.getUsername());

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository, times(1)).save(userCaptor.capture());

        User savedUser = userCaptor.getValue();
        assertEquals(userRequestDTO.getUsername(), savedUser.getUsername());
    }

    @Test
    void shouldSendEmailToUser() {
        String userId = "1";
        String message = "Test message";

        User user = new User();
        user.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        userService.sendEmailToUser(userId, message);

        verify(sendgridSender, times(1)).send(Collections.singletonList(user), message);
        verify(userRepository, times(1)).findById(userId);
    }
    @Test
    void testDeleteUser() {
        String userId = "user123";
        User userToDelete = new User();
        when(userRepository.findById(userId)).thenReturn(Optional.of(userToDelete));

        userService.deleteUser(userId);

        Mockito.verify(userRepository, Mockito.times(1)).delete(userToDelete);
    }

    @Test
    void testFilterByEmail() {
        String userEmail = "test@example.com";
        User userInRepository = new User();
        when(userRepository.findFirstByEmailLike(userEmail)).thenReturn(Optional.of(userInRepository));

        UserDTO result = userService.filterByEmail(userEmail);

        assertNotNull(result);
    }
}
