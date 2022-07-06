package fun.epak.pak.service;

import fun.epak.pak.infrastructure.NewPostRequest;
import fun.epak.pak.infrastructure.PageData;
import fun.epak.pak.model.Post;
import fun.epak.pak.model.user.User;
import fun.epak.pak.repository.PostRepository;
import fun.epak.pak.repository.UserRepository;
import fun.epak.pak.utility.ImageAddressUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    @Value("${image.address}")
    private String imageBaseAddress;
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
        String userImagePath = ImageAddressUtil.UserImage(imageBaseAddress, post.getUser());
        return PageData.of(post, userImagePath);
    }

    public void saveNewPost(NewPostRequest post, String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        Post newPost = Post.builder()
                .user(user)
                .content(post.getContent())
                .createDate(LocalDate.now())
                .build();

        postRepository.save(newPost);
    }
}
