package fun.epak.pak.controller;

import fun.epak.pak.infrastructure.ChangeUserDataRequest;
import fun.epak.pak.infrastructure.OtherUserProfileData;
import fun.epak.pak.infrastructure.UserProfileData;
import fun.epak.pak.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;

    @GetMapping("/my-profile")
    public String getMyProfile(Model model, Principal principal) {
        if (principal == null) {
            return "/auth/auth";
        }
        UserProfileData user = userService.loadUserProfileData(principal.getName());
        model.addAttribute("user", user);
        return "/profile/profile";
    }

    @PostMapping("/my-profile")
    public RedirectView postUpdateToMyPRofile(ChangeUserDataRequest userData,
                                              @RequestParam("image") MultipartFile multipartFile,
                                              Principal principal) {
        userService.saveChangedUser(userData, multipartFile, principal.getName());
        return new RedirectView("/my-profile");
    }

    @GetMapping("/profile/{id}")
    public String getOtherProfile(@PathVariable long id, Model model, Principal principal) {
        OtherUserProfileData user = userService.loadOtherUserProfileData(principal.getName(), id);
        model.addAttribute("user", user);
        return "/profile/otherProfile";
    }

    @PostMapping("/subscriptions")
    public RedirectView changeSubscriptionList(long id, Principal principal) {
        userService.changeUserSubscriptionList(principal.getName(), id);
        return new RedirectView("/profile/" + id);
    }
}
