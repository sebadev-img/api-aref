package dev.seba.apiaref.service.Impl;

import dev.seba.apiaref.client.PostRestClient;
import dev.seba.apiaref.dto.response.PostsResponseDto;
import dev.seba.apiaref.model.Post;
import dev.seba.apiaref.service.IPostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements IPostService {

    private final PostRestClient postClient;

    public PostServiceImpl(PostRestClient postRestClient){
        this.postClient = postRestClient;
    }
    @Override
    public PostsResponseDto getAll() {
        List<Post> posts = postClient.findAll();
        PostsResponseDto postDto = new PostsResponseDto();
        postDto.setCount(posts.size());
        postDto.setResults(posts);
        return postDto;
    }

    @Override
    public Post getById(int id) {
        return postClient.findById(id);
    }

    @Override
    public PostsResponseDto getPostsByUserId(int userId) {
        List<Post> posts = postClient.findByUserId(userId);
        PostsResponseDto postDto = new PostsResponseDto();
        postDto.setCount(posts.size());
        postDto.setResults(posts);
        return postDto;
    }

    @Override
    public PostsResponseDto getPostsByTextInBody(String text) {
        List<Post> posts = postClient.findAll();
        List<Post> postsWithText = posts.stream().filter(post ->
            post.body().contains(text)).toList();
        PostsResponseDto postDto = new PostsResponseDto();
        postDto.setCount(postsWithText.size());
        postDto.setResults(postsWithText);
        return postDto;
    }
}
