package service;

import lombok.RequiredArgsConstructor;
import model.Post;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class postService {
    private final repository.postRepository.PostRepository postRepository;

    public void savePost(Post post) {
        postRepository.save(post);
    }

    public Post loadById(Long id) {
        return postRepository.findById(id).orElseThrow();
    }

}
