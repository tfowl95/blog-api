package com.tfowl95.blog_api.domain;

import java.time.Instant;
import java.util.List;

public record Post(
    Long id,
    String title,
    String content,
    String category,
    List<String> tags,
    Instant createdAt,
    Instant updatedAt
) {}