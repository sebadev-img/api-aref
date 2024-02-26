package dev.seba.apiaref.dto.response;

import dev.seba.apiaref.model.Post;
import lombok.Data;

import java.util.List;

@Data
public class PostsResponseDto {
    private int count;
    private List<Post> results;
}
