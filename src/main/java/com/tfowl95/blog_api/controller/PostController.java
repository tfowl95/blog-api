package com.tfowl95.blog_api.controller;

import java.time.Instant;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tfowl95.blog_api.domain.Post;
import com.tfowl95.blog_api.domain.PostRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/posts")
public class PostController {

    @GetMapping
    List<Post> getAllPosts(@RequestParam(required = false) String term) {
        // Stub
        return List.of(new Post(
            1L, "Stubbed Post #1", "Content #1", "Tech #1",
            List.of("Java #1"), Instant.now(), Instant.now()
        ));
    }

    @GetMapping("/{id}")
    public Post getPost(@PathVariable Long id) {
        // Stub
        return new Post(
            2L, "Stubbed Post #2", "Content #2", "Tech #2",
            List.of("Java #2"), Instant.now(), Instant.now()
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(@Valid @RequestBody PostRequest request) {
        // Stub
        return new Post(
            3L, "Stubbed Post #3", "Content #3", "Tech #3",
            List.of("Java #3"), Instant.now(), Instant.now()
        );
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable Long id, @Valid @RequestBody PostRequest request) {
        // Stub
        return new Post(
            id, request.title(), request.content(), request.category(), request.tags(), Instant.now(), Instant.now());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable Long id) {
        // Stub
    }

}
