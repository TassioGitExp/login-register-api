package study.tassio.controller;

import org.springframework.web.bind.annotation.*;
import study.tassio.controller.request.UserRequest;
import study.tassio.controller.response.UserResponse;
import study.tassio.model.entities.User;
import study.tassio.model.services.UserService;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    // Injeção de dependência --->
    private UserService userService;

    public UserController(){}

    public UserController(UserService userService) {
        this.userService = userService;
    }
    // <---

    @PostMapping("/signup")
    public UserResponse signup(@RequestBody UserRequest userRequest) {
        var user = userRequest.toUser();

        return new UserResponse().fromUser(userService.create(user));
    }

    @PostMapping("/signin")
    public void signin(@RequestParam("email") String email, @RequestParam("password") String password) {
        userService.signin(email, password);
    }

    @PostMapping("/signout")
    public void signout(@RequestParam("email") String email) {
        userService.signout(email);
    }

    @GetMapping("/list+all")
    public List<User> getAll() {
        return userService.findAll();
    }

}
