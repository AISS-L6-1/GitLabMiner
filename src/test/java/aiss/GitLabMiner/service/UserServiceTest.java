package aiss.GitLabMiner.service;

import aiss.GitLabMiner.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;


@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    @DisplayName("Test de getAllUsers")
    void getAllUsers() {
        List<User> userList = userService.getAllUsers(20, null);
        assertFalse(userList.isEmpty(), "The list of users is empty");
        System.out.println(userList);
    }

    @Test
    @DisplayName("Test de getUser")
    void getUser() {
        User user = userService.getUser(14175568);
        assertNotNull(user.getId(), "The id cannot be null");
        assertNotNull(user.getUsername(), "The user name cannot be null");
        assertNotNull(user.getName(), "The name cannot be null");
        assertNotNull(user.getAvatarUrl(), "The avatar url cannot be null");
        assertNotNull(user.getWebUrl(), "The url cannot be null");
        System.out.println(user);
    }
}