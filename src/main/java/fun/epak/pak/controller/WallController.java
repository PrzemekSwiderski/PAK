package fun.epak.pak.controller;

import fun.epak.pak.model.Post;
import fun.epak.pak.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class WallController {
    private final PostService postService;

    @GetMapping("/wall/exploration")
    public String getExploration(Model model) {
        List<Post> posts = postService.loadAllPost();
        model.addAttribute("allPosts", posts);
        return "/wall/exploration";
    }
}





