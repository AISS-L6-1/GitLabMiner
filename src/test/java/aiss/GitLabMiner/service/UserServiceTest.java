package aiss.GitLabMiner.service;

import aiss.GitLabMiner.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    @DisplayName("Test de getAllUsers")
    void getAllUsers() {
        List<User> userList = userService.getAllUsers(20, null);
        System.out.println(userList);
    }

    @Test
    @DisplayName("Test de getUser")
    void getUser() {
        User user = userService.getUser(14175568);
        System.out.println(user);
    }
}