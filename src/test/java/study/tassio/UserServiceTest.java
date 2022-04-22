package study.tassio;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mockitoSession;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import study.tassio.model.entities.User;
import study.tassio.model.repositories.UserRepository;
import study.tassio.model.services.UserService;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    private User user = new User();

    //User user = Mockito.mock(User.class);

    @Before
    public void setup(){
        user.setId("abc12345");
        user.setFirstName("userFirstName");
        user.setLastName("userLastName");
        user.setEmail("user@email.com");
        user.setPassword("userPassword");
        user.setDateOfBirth("01/02/2000");

        Optional<User> userOptional = Optional.of(user); 
        
        Mockito.when(userRepository.findById(user.getId())).thenReturn(userOptional);
        Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(userOptional);

        Mockito.when(userRepository.findAll()).thenReturn(List.of(user));
    
    }

    @Test
    public void createUserTest() {
        userService.create(user);

        User testUser = userService.findById("abc12345");

        assertFalse("User not created.", testUser == null);
        assertEquals("Incorrect user returned.", user, testUser);
        assertEquals("Incorrect user first name.", "userFirstName", testUser.getFirstName());
        assertEquals("Incorrect user last name.", "userLastName", testUser.getLastName());
        assertEquals("Incorret user email.", "user@email.com", testUser.getEmail());
        assertEquals("Incorrect user password.", "userPassword", testUser.getPassword());
        assertEquals("Incorrect date of birth.", "01/02/2000", testUser.getDateOfBirth());
       
    }

    @Test
    public void findByIdTest(){
        User testUser = userService.findById("abc12345");

        assertEquals("Incorrect user returned.", user, testUser);
        assertEquals("Incorrect user ID returned.", user.getId(), testUser.getId());

    }

    @Test
    public void findByEmailTest(){
        User testUser = userService.findByEmail("user@email.com");

        assertEquals("Incorrect user returned.", user, testUser);
        assertEquals("Incorrect user email returned.", user.getEmail(), testUser.getEmail());
    
    }

    @Test
    public void findAllUsersTest() {
        List<User> usersList = userService.findAll();
        
        assertNotNull("No objects returned.", usersList);
        assertEquals("Incorrect number of objects returned.", 1, usersList.size());
    
    }
}
