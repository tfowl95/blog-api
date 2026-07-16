package com.tfowl95.blog_api.repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import com.tfowl95.blog_api.domain.Post;
import com.tfowl95.blog_api.domain.PostRequest;

@Repository
public class PostRepository {

    private final JdbcClient jdbcClient;
    private final RowMapper<Post> postRowMapper = (rs, rowNum) -> new Post(
        rs.getLong("id"),
        rs.getString("title"),
        rs.getString("content"),
        rs.getString("category"),
        Arrays.asList((String[]) rs.getArray("tags").getArray()),
        rs.getTimestamp("created_at").toInstant(),
        rs.getTimestamp("updated_at").toInstant()
    );

    public PostRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public Post save(PostRequest postRequest) {
        String sql = """
                INSERT INTO posts (title, content, category, tags)
                VALUES (:title, :content, :category, :tags)
                RETURNING *
                """;
        
        return jdbcClient.sql(sql)
            .param("title", postRequest.title())
            .param("content", postRequest.content())
            .param("category", postRequest.category())
            .param("tags", postRequest.tags().toArray(new String[0]))
            .query(postRowMapper)
            .single();
    }

    public Optional<Post> findById(Long id) {
        return jdbcClient.sql("SELECT * FROM posts WHERE id = :id")
            .param("id", id)
            .query(postRowMapper)
            .optional();
    }

    public List<Post> findAll(String term) {
        if (term == null || term.isBlank()) {
            return jdbcClient.sql("SELECT * FROM posts ORDER BY created_at DESC")
                .query(postRowMapper)
                .list();
        }

        String sql = """
                SELECT *
                FROM posts
                WHERE title ILIKE :term
                    OR content ILIKE :term
                    OR category ILIKE :term
                ORDER BY created_at DESC
                """;
        
        return jdbcClient.sql(sql)
            .param("term", "%" + term + "%")
            .query(postRowMapper)
            .list();
    }

    public Optional<Post> update(Long id, PostRequest postRequest) {
        String sql = """
                UPDATE posts
                SET title = :title, content = :content, category = :category,
                tags = :tags, updated_at = CURRENT_TIMESTAMP
                WHERE id = :id
                RETURNING *
                """;
        
        return jdbcClient.sql(sql)
            .param("title", postRequest.title())
            .param("content", postRequest.content())
            .param("category", postRequest.category())
            .param("tags", postRequest.tags().toArray(new String[0]))
            .param("id", id)
            .query(postRowMapper)
            .optional();
    }

    public boolean delete(Long id) {
        int rowsAffected = jdbcClient.sql("DELETE FROM posts WHERE id = :id")
            .param("id", id)
            .update();
        
            return rowsAffected > 0;
    }
}