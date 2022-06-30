package fun.epak.pak.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping
    public String getIndex(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
/*        User user = null;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            user =  userService.loadUserById(authentication.getName());
        }*/
        model.addAttribute("test", authentication.getName());

        return "index";
    }
}
