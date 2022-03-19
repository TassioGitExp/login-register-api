package study.tassio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import study.tassio.controller.request.UserRequest;
import study.tassio.controller.response.UserResponse;
import study.tassio.model.entities.User;
import study.tassio.model.services.UserService;

import java.lang.ProcessBuilder.Redirect;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    // Injeção de dependência --->
    @Autowired
    private UserService userService;

    public UserController(){}

    // public UserController(UserService userService) {
    //     this.userService = userService;
    // }
    

    @PostMapping(path = "/sign-up", 
    consumes = "application/json",
    produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserResponse signup(@RequestBody UserRequest userRequest) {
        var user = userRequest.toUser();
        System.out.println("+\n+\n+\n"+user.toString()+"\n+\n+\n+");

        return new UserResponse().fromUser(userService.create(user));
    }

    @PostMapping("/sign-in")
    public void signin(@RequestParam("email") String email, 
                        @RequestParam("password") String password, 
                        @RequestParam("remember") Boolean rememberMe) {
        userService.signin(email, password, rememberMe);
        System.out.println("sign-in");    
    }

    @PostMapping("/sign-out")
    public void signout(@RequestParam("id") String id) {
        userService.signout(id);
    }

    @PostMapping("/user-offline")
    public void isOffline(@RequestParam("id") String id) {
        userService.isOffline(id);
    }

    @GetMapping("/list-all")
    public List<User> getAll() {
        return userService.findAll();
    }

}
