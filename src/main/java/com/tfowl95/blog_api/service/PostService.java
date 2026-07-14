package com.tfowl95.blog_api.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tfowl95.blog_api.domain.Post;
import com.tfowl95.blog_api.domain.PostRequest;

@Service
public class PostService {

    public List<Post> findAll(String term) {
        // Stub
        return List.of(new Post(
            1L, "Stubbed Post #1", "Content #1", "Tech #1",
            List.of("Java #1"), Instant.now(), Instant.now()
        ));
    }

    public Optional<Post> findById(Long id) {
        // Stub
        return Optional.of(
            new Post(
                2L, "Stubbed Post #2", "Content #2", "Tech #2",
                List.of("Java #2"), Instant.now(), Instant.now()
            )
        );
    }

    public Post create(PostRequest postRequest) {
        // Stub
        return new Post(
            3L, postRequest.title(), postRequest.content(), postRequest.category(),
            postRequest.tags(), Instant.now(), Instant.now()
        );
    }

    public Post update(Long id, PostRequest postRequest) {
        // Stub
        return new Post(
            id, postRequest.title(), postRequest.content(), postRequest.category(),
            postRequest.tags(), Instant.now(), Instant.now()
        );
    }

    public void delete(Long id) {
        // Stub
    }

}
