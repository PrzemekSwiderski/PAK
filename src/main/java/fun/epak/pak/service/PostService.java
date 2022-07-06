package fun.epak.pak.service;

import fun.epak.pak.infrastructure.NewPostRequest;
import fun.epak.pak.infrastructure.PageData;
import fun.epak.pak.infrastructure.ViewCommentData;
import fun.epak.pak.model.Comment;
import fun.epak.pak.model.Post;
import fun.epak.pak.model.user.User;
import fun.epak.pak.repository.PostRepository;
import fun.epak.pak.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public List<PageData> loadAllPageData() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(this::mapToPageData)
                .sorted(Comparator.comparing(PageData::getPostId).reversed())
                .collect(Collectors.toList());
    }

    private PageData mapToPageData(Post post) {
        String userImagePath =
                "/data/images/profiles/"
                        + post.getUser().getId()
                        + "/" + post.getUser().getImageName();
        List<ViewCommentData> comments = post.getComments().stream().map(this::mapToViewCommentsData
        ).collect(Collectors.toList());
        return PageData.of(post, userImagePath, comments);
    }

    private ViewCommentData mapToViewCommentsData(Comment comment) {
        String commentingUserImagePath =
                "/data/images/profiles/"
                        + comment.getUser().getId()
                        + "/" + comment.getUser().getImageName();
        return ViewCommentData.of(comment, commentingUserImagePath);
    }

    public void saveNewPost(NewPostRequest post, String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        Post newPost = new Post();
        newPost.setUser(user);
        newPost.setContent(post.getContent());
        postRepository.save(newPost);
    }
}
