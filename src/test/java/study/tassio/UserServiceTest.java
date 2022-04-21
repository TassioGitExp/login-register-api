package study.tassio;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import study.tassio.model.entities.User;
import study.tassio.model.services.UserService;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void FindAllTest() {
        List<User> usersList = userService.findAll();

        assertNotNull("No objects returned.", usersList);
        assertEquals("Incorrect number of objects returned.", 1, usersList.size());
    }
    
}
