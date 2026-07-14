package com.tfowl95.blog_api.domain;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record PostRequest(
    @NotBlank(message = "Title is required") String title,
    @NotBlank(message = "Content is required") String content,
    @NotBlank(message = "Category is required") String category,
    @NotEmpty(message = "At least one tag is required") List<String> tags
) {}
