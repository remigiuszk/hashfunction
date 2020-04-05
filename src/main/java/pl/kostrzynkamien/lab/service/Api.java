package pl.kostrzynkamien.lab.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class Api {

    @GetMapping("/forUser")
    public String forUser(Principal principal) {
        return "Cześć user: " + principal.getName();
    }
}
