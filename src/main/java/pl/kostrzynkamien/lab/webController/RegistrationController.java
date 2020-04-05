package pl.kostrzynkamien.lab.webController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.kostrzynkamien.lab.model.User;
import pl.kostrzynkamien.lab.repo.UserRepo;
import pl.kostrzynkamien.lab.service.UserService;

import javax.servlet.http.HttpServletRequest;


@Controller
public class RegistrationController {
    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;
    private UserService userService;

    @Autowired
    public RegistrationController(UserRepo userRepo, PasswordEncoder passwordEncoder, UserService userService) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/signUp")
    public ModelAndView signUp() {
        return new ModelAndView("registration", "user", new User());
    }

    @RequestMapping("/register")
    public ModelAndView register(User user, HttpServletRequest httpServletRequest) {
        try {
            userService.addNewUser(user, httpServletRequest);
        } catch (javax.mail.MessagingException e) {
        }
        return new ModelAndView("redirect:/login");
    }

    @RequestMapping("/verify-token")
    public ModelAndView verifyToken(@RequestParam String token) {
        userService.verifyToken(token);
        return new ModelAndView("redirect:/login");
    }

}