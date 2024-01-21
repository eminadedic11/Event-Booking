package ba.edu.ibu.eventbooking.core.model;

import ba.edu.ibu.eventbooking.core.model.enums.UserType;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class UserTest {

    @Test
    void testGettersAndSetters() {
        User user = new User();
        user.setId("1");
        user.setUserType(UserType.ADMIN);
        user.setFirstName("Emina");
        user.setLastName("Dedic");
        user.setEmail("emina.dedic@gmail.com");
        user.setUsername("eminadedic");
        user.setPassword("123456");
        Date creationDate = new Date();
        user.setCreationDate(creationDate);

        assertEquals("1", user.getId());
        assertEquals(UserType.ADMIN, user.getUserType());
        assertEquals("Emina", user.getFirstName());
        assertEquals("Dedic", user.getLastName());
        assertEquals("emina.dedic@gmail.com", user.getEmail());
        assertEquals("eminadedic", user.getUsername());
        assertEquals("123456", user.getPassword());
        assertEquals(creationDate, user.getCreationDate());
    }

    @Test
    void testAuthorities() {
        User user = new User();
        user.setUserType(UserType.REGISTERED_USER);

        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();


        assertEquals(1, authorities.size());
        assertTrue(authorities.contains(new SimpleGrantedAuthority(UserType.REGISTERED_USER.name())));
    }

    @Test
    void testAccountExpiration() {
        User user = new User();
        assertTrue(user.isAccountNonExpired());
    }

    @Test
    void testAccountLocking() {
        User user = new User();
        assertTrue(user.isAccountNonLocked());
    }

    @Test
    void testCredentialsExpiration() {
        User user = new User();
        assertTrue(user.isCredentialsNonExpired());
    }

    @Test
    void testEnabled() {
        User user = new User();
        assertTrue(user.isEnabled());
    }
}