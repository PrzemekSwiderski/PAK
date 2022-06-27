package fun.epak.pak.controller;

import fun.epak.pak.infrastructure.UserRegistrationRequest;
import fun.epak.pak.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/auth")
    public String getLogin() {
        return "/auth/auth";
    }

    @PostMapping("/register")
    public RedirectView postNewUser(UserRegistrationRequest userRegistrationRequest) {
        userService.registerUser(userRegistrationRequest);
        return new RedirectView("/auth");
    }

}
