package fun.epak.pak.controller;

import fun.epak.pak.infrastructure.NewCommentRequest;
import fun.epak.pak.infrastructure.NewPostRequest;
import fun.epak.pak.infrastructure.PageData;
import fun.epak.pak.infrastructure.UserWritingPostData;
import fun.epak.pak.service.CommentService;
import fun.epak.pak.service.PostService;
import fun.epak.pak.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class WallController {
    private final PostService postService;
    private final UserService userService;
    private final CommentService commentService;

    @GetMapping
    public String getMainWall(Model model, Principal principal){
        List<PageData> posts = postService.loadAllMainWallPageData(principal.getName());
        UserWritingPostData user = userService.loadUserWritingPostData(principal.getName());
        model.addAttribute("posts", posts);
        model.addAttribute("user", user);
        return "index";
    }

    @GetMapping("/exploration")
    public String getExploration(Model model, Principal principal) {
        List<PageData> posts = postService.loadAllPageData();
        UserWritingPostData user = userService.loadUserWritingPostData(principal.getName());
        model.addAttribute("posts", posts);
        model.addAttribute("user", user);
        return "/wall/exploration";
    }

    @PostMapping("/new-post")
    public RedirectView postNewPost(NewPostRequest post, Principal principal) {
        postService.saveNewPost(post, principal.getName());
        return new RedirectView("/exploration");
    }

    @PostMapping("/comment")
    public RedirectView postNewComment(NewCommentRequest comment, Principal principal) {
        commentService.addNewComment(comment, principal.getName());
        return new RedirectView("/exploration");
    }
}





