package study.tassio.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
    

    @PostMapping("/sign-up")
    public UserResponse signup(@RequestBody UserRequest userRequest) {
        var user = userRequest.toUser();
        System.out.println("test");

        return new UserResponse().fromUser(userService.create(user));
    }

    // Teste com metodo void, sem UserResponse
    // @PostMapping("/sign-up")
    // public void signup(@RequestBody UserRequest userRequest) {
    //     var user = userRequest.toUser();
    //     System.out.println(user);

    //     userService.create(user);
    // }

    // Teste de redirect
    // @PostMapping("/signup")
    // public ModelAndView signup() {
       
    //     System.out.println("teste");
    
    //     // Meu deus isso funcionou
    //     return new ModelAndView("redirect:/");
    // }

    @PostMapping("/sign-in")
    public void signin(@RequestParam("email") String email, @RequestParam("password") String password) {
        userService.signin(email, password);
    }

    @PostMapping("/sign-out")
    public void signout(@RequestParam("email") String email) {
        userService.signout(email);
    }

    @GetMapping("/list+all")
    public List<User> getAll() {
        return userService.findAll();
    }

}
