package com.tfowl95.blog_api.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tfowl95.blog_api.domain.Post;
import com.tfowl95.blog_api.domain.PostRequest;
import com.tfowl95.blog_api.repository.PostRepository;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> findAll(String term) {
        return postRepository.findAll(term);
    }

    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    public Post create(PostRequest postRequest) {
        return postRepository.save(postRequest);
    }

    public Post update(Long id, PostRequest postRequest) {
        return postRepository.update(id, postRequest).get();
    }

    public void delete(Long id) {
        boolean deleteSuccessful = postRepository.delete(id);
    }

}
