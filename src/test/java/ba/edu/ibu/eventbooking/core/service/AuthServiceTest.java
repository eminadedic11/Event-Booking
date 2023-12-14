package ba.edu.ibu.eventbooking.core.service;


import ba.edu.ibu.eventbooking.core.exceptions.repository.ResourceNotFoundException;
import ba.edu.ibu.eventbooking.core.model.User;
import ba.edu.ibu.eventbooking.core.repository.UserRepository;
import ba.edu.ibu.eventbooking.rest.dto.LoginDTO;
import ba.edu.ibu.eventbooking.rest.dto.LoginRequestDTO;
import ba.edu.ibu.eventbooking.rest.dto.UserDTO;
import ba.edu.ibu.eventbooking.rest.dto.UserRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@AutoConfigureMockMvc
@SpringBootTest
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthService authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSignInUserNotFound() {
        // Arrange
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO("test@example.com", "password");
        loginRequestDTO.setEmail("nonexistent@example.com");
        loginRequestDTO.setPassword("password");

        when(userRepository.findByEmail(loginRequestDTO.getEmail())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> authService.signIn(loginRequestDTO));
    }

    @Test
    void testSignInAuthenticationFailure() {
        // Arrange
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO("test@example.com", "password");
        loginRequestDTO.setEmail("test@example.com");
        loginRequestDTO.setPassword("wrongPassword");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new UsernameNotFoundException("Authentication failed"));

        // Act and Assert
        assertThrows(UsernameNotFoundException.class, () -> authService.signIn(loginRequestDTO));
    }
}
