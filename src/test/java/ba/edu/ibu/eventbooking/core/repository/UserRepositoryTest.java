package ba.edu.ibu.eventbooking.core.repository;

import ba.edu.ibu.eventbooking.core.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindAllCustom() {
        List<User> allUsers = userRepository.findAllCustom();
        assertTrue(allUsers.size() > 0, "At least one user should be returned");
    }
    @Test
    public void testFindFirstByEmailLike() {
        String emailPattern = "emina.dedic@stu.ibu.edu.ba";

        Optional<User> foundUser = userRepository.findFirstByEmailLike(emailPattern);

        assertTrue(foundUser.isPresent(), "User should be found");
        assertTrue(foundUser.get().getEmail().contains(emailPattern), "Found user email should contain the specified pattern");
    }
}
