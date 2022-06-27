package fun.epak.pak.controller;

import fun.epak.pak.infrastructure.UserRegistrationRequest;
import fun.epak.pak.model.user.User;
import fun.epak.pak.service.UserService;
import fun.epak.pak.utility.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/auth")
    public String getLogin() {
        return "/auth/auth";
    }

    @PostMapping("/register")
    public RedirectView postNewUser(UserRegistrationRequest userRegistrationRequest,
                                    @RequestParam("image") MultipartFile multipartFile) throws IOException {
        User user = userService.registerUser(userRegistrationRequest, multipartFile);
        String uploadDir = "data/images/profiles/" + user.getId();

        FileUploadUtil.saveFile(uploadDir, user.getImageName(), multipartFile);
        return new RedirectView("/auth");
    }

}
