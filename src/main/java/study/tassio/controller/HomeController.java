package study.tassio.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("home/index.html");

        return mv;
    }

    @GetMapping("/sign-up")
    public ModelAndView signup() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("signUp/index.html");

        return mv;
    }

    @GetMapping("/sign-in")
    public ModelAndView signin() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("signIn/index.html");

        return mv;
    }
}
