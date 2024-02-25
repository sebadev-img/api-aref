package dev.seba.apiaref.service;

import dev.seba.apiaref.dto.response.PostsResponseDto;
import dev.seba.apiaref.model.Post;

public interface IPostService {
    PostsResponseDto getAll();
    Post getById(int id);
    PostsResponseDto getPostsByUserId(int userId);

}
