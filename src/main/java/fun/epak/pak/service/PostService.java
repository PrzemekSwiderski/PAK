package fun.epak.pak.service;

import fun.epak.pak.exceptions.SaveFileException;
import fun.epak.pak.infrastructure.NewPostRequest;
import fun.epak.pak.infrastructure.PageData;
import fun.epak.pak.infrastructure.ViewCommentData;
import fun.epak.pak.model.Comment;
import fun.epak.pak.model.Post;
import fun.epak.pak.model.user.User;
import fun.epak.pak.repository.PostRepository;
import fun.epak.pak.repository.UserRepository;
import fun.epak.pak.utility.FileUploadUtil;
import fun.epak.pak.utility.ImageAddressUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PostService {

    @Value("${user.image.address}")
    private String userImageBaseAddress;
    @Value("${post.image.address}")
    private String postImageBaseAddress;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    Logger logger = LoggerFactory.getLogger(UserService.class);

    public List<PageData> loadAllPageData() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(this::mapToPageData)
                .sorted(Comparator.comparing(PageData::getPostId).reversed())
                .collect(Collectors.toList());
    }

    private PageData mapToPageData(Post post) {
        String userImagePath = ImageAddressUtil.userImage(userImageBaseAddress, post.getUser());
        List<ViewCommentData> comments = post.getComments().stream()
                .map(this::mapToViewCommentsData)
                .collect(Collectors.toList());
        if (post.getImageName() != null) {
            String postImagePath = ImageAddressUtil.postImage(postImageBaseAddress, post);
            return buildPageDataWithImage(post, userImagePath, postImagePath, comments);
        } else {
            return buildPageDataWithoutImage(post, userImagePath, comments);
        }

    }

    private ViewCommentData mapToViewCommentsData(Comment comment) {
        String commentingUserImagePath = ImageAddressUtil.userImage(userImageBaseAddress, comment.getUser());
        return ViewCommentData.of(comment, commentingUserImagePath);
    }

    private PageData buildPageDataWithImage(Post post, String userImagePath, String postImagePath, List<ViewCommentData> comments) {
        return PageData.builder()
                .userId(post.getUser().getId())
                .username(post.getUser().getUsername())
                .userImageAddress(userImagePath)
                .postId(post.getId())
                .content(post.getContent())
                .postImageAddress(postImagePath)
                .createDate(post.getCreateDate())
                .comments(comments)
                .build();
    }

    private PageData buildPageDataWithoutImage(Post post, String userImagePath, List<ViewCommentData> comments) {
        return PageData.builder()
                .userId(post.getUser().getId())
                .username(post.getUser().getUsername())
                .userImageAddress(userImagePath)
                .postId(post.getId())
                .content(post.getContent())
                .createDate(post.getCreateDate())
                .comments(comments)
                .build();
    }

    public void saveNewPost(NewPostRequest post, MultipartFile multipartFile, String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        if (!multipartFile.isEmpty()) {
            Post newPost = Post.builder()
                    .user(user)
                    .content(post.getContent())
                    .imageName(multipartFile.getOriginalFilename())
                    .createDate(LocalDate.now())
                    .build();
            Post savedPost = postRepository.save(newPost);
            saveFile(multipartFile, savedPost);
        } else {
            Post newPost = Post.builder()
                    .user(user)
                    .content(post.getContent())
                    .createDate(LocalDate.now())
                    .build();
            Post savedPost = postRepository.save(newPost);
        }
    }

    private void saveFile(MultipartFile multipartFile, Post post) {
        String uploadDir = "data/images/post/" + post.getId();
        try {
            FileUploadUtil.saveFile(uploadDir, post.getImageName(), multipartFile);
        } catch (IOException e) {
            logger.error(e.getLocalizedMessage());
            throw new SaveFileException("Error at file upload at: " + uploadDir + "with file: " + post.getImageName());
        }
    }

    public List<PageData> loadAllMainWallPageData(String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        Set<User> subscriptions = user.getSubscriptions();
        List<Post> allPostsBySubs = getPostsOfSubscribed(user, subscriptions);
        List<Post> allPostsByComments = getPostsWithUserOrSubscribedComments(user, subscriptions);
        Set<Post> allMainWallPosts = getAllPosts(allPostsBySubs, allPostsByComments);
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
        Stream<Long> postIdFromComments = extracPostIdsFromComments(comments);
        Stream<Long> postIdsCommentedBySubs = extractPostIdsCommentedBySubs(subscriptions);
        Set<Long> allPostsIds = Stream.concat(postIdFromComments, postIdsCommentedBySubs).collect(Collectors.toSet());
        return postRepository.findAllById(allPostsIds);
    }

    private Stream<Long> extractPostIdsCommentedBySubs(Set<User> subscriptions) {
        return subscriptions.stream()
                .flatMap(user -> user.getComments().stream())
                .map(Comment::getPost)
                .map(Post::getId);
    }

    private Stream<Long> extracPostIdsFromComments(List<Comment> comments) {
        return comments.stream()
                .map(Comment::getPost)
                .map(Post::getId);
    }

    private Set<Post> getAllPosts(List<Post> allPostsBySubs, List<Post> allPostsByComments) {
        return Stream.concat(allPostsBySubs.stream(), allPostsByComments.stream())
                .collect(Collectors.toSet());
    }
}
