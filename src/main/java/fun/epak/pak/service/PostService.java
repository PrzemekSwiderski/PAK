package fun.epak.pak.service;

import fun.epak.pak.model.Post;
import fun.epak.pak.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public void savePost(Post post) {
        postRepository.save(post);
    }

    public Post loadById(Long id) {
        return postRepository.findById(id).orElseThrow();
    }


    public Page<Post> fetchAllPosts(int pageNo, int size) {
        PageRequest pageRequest = PageRequest.of(pageNo, size);
        Page<Post> postPage = postsRepository.findAll(pageRequest);
        return postPage;
    }

}
//zapisanie posta
//wczytanie posta po Id
//wczytanie 10 postow (paginacja)pageable
//