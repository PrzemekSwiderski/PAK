package fun.epak.pak.service;

import fun.epak.pak.infrastructure.PageData;
import fun.epak.pak.model.Post;
import fun.epak.pak.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void savePost(Post post) {
        postRepository.save(post);
    }

    public Post loadPostById(Long id) {
        return postRepository.findById(id).orElseThrow();
    }
    public List<PageData> loadAllPageData() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(post -> {
            String userImagePath =
                    "/data/images/profiles/"
                            + post.getUser().getId()
                            + "/" + post.getUser().getImageName();
            return PageData.of(post, userImagePath);
        }).collect(Collectors.toList());
    }


}
//zapisanie posta
//wczytanie posta po Id

//