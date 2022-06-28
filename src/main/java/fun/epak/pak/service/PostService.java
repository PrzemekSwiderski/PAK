package fun.epak.pak.service;

import fun.epak.pak.model.Post;
import fun.epak.pak.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Post> loadAllPost() {
        return postRepository.findAll();
    }


   /* public Page<Post> fetchAllPosts(int pageNo, int size) {
        PageRequest pageRequest = PageRequest.of(pageNo, size);
        Page<Post> postPage = postRepository.findAll(pageRequest);
        return postPage;
    }*/

}
//zapisanie posta
//wczytanie posta po Id

//