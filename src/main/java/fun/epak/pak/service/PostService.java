package fun.epak.pak.service;

import fun.epak.pak.infrastructure.NewPostRequest;
import fun.epak.pak.infrastructure.PageData;
import fun.epak.pak.infrastructure.ViewCommentData;
import fun.epak.pak.model.Comment;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        String userImagePath = ImageAddressUtil.userImage(imageBaseAddress, post.getUser());
        List<ViewCommentData> comments = post.getComments().stream()
                .map(this::mapToViewCommentsData)
                .collect(Collectors.toList());
        return PageData.of(post, userImagePath, comments);
    }

    private ViewCommentData mapToViewCommentsData(Comment comment) {
        String commentingUserImagePath = ImageAddressUtil.userImage(imageBaseAddress, comment.getUser());
        return ViewCommentData.of(comment, commentingUserImagePath);
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

    public List<PageData> loadAllMainWallPageData(String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        Set<User> subscriptions = user.getSubscriptions();
        List<Post> allPostsBySubs = getPostsOfSubscribed(user, subscriptions);
        List<Post> allPostsByComments = getPostsWithUserOrSubscribedComments(user, subscriptions);
        List<Post> allMainWallPosts = getAllPosts(allPostsBySubs, allPostsByComments);
        return allMainWallPosts.stream()
                .map(this::mapToPageData)
                .sorted(Comparator.comparing(PageData::getPostId).reversed())
                .collect(Collectors.toList());
    }

    private List<Post> getPostsOfSubscribed(User user, Set<User> subscriptions) {
        Set<Long> subsIds = subscriptions.stream()
                .map(User::getId)
                .collect(Collectors.toSet());
        subsIds.add(user.getId());
        return postRepository.findAllByUser_IdIn(subsIds);
    }

    private List<Post> getPostsWithUserOrSubscribedComments(User user, Set<User> subscriptions) {
        List<Comment> comments = user.getComments();
        Set<Long> setOfPostIdFromComments = comments.stream()
                .map(Comment::getPost)
                .map(Post::getId)
                .collect(Collectors.toSet());
        Set<Long> setOfPostIdsCommentedBySubs = subscriptions.stream()
                .map(User::getComments)
                .map(com -> com.stream().map(Comment::getPost))
                .map(post -> post.map(Post::getId))
                .reduce(Stream::concat).orElse(Stream.<Long>builder().build())
                .collect(Collectors.toSet());
        Set<Long> allPostsIds = new HashSet<>(setOfPostIdFromComments);
        allPostsIds.addAll(setOfPostIdsCommentedBySubs);
        return postRepository.findAllById(allPostsIds);
    }

    private List<Post> getAllPosts(List<Post> allPostsBySubs, List<Post> allPostsByComments) {
        return Stream.concat(allPostsBySubs.stream(), allPostsByComments.stream())
                .distinct()
                .collect(Collectors.toList());
    }
}
