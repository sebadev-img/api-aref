package dev.seba.apiaref.service.Impl;

import dev.seba.apiaref.client.PostRestClient;
import dev.seba.apiaref.client.UserRestClient;
import dev.seba.apiaref.dto.response.PostsResponseDto;
import dev.seba.apiaref.exception.PostNotFoundException;
import dev.seba.apiaref.exception.UserNotFoundException;
import dev.seba.apiaref.model.Post;
import dev.seba.apiaref.model.User;
import dev.seba.apiaref.service.IPostService;
import dev.seba.apiaref.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements IPostService {

    private final PostRestClient postClient;
    private final IUserService userService;

    public PostServiceImpl(PostRestClient postRestClient, UserServiceImpl userService){
        this.postClient = postRestClient;
        this.userService = userService;
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
        List<Post> posts = postClient.findAll();
        Optional<Post> postOptional = posts.stream().filter(post->
                post.id() == id
        ).findFirst();
        if(postOptional.isEmpty()){
            throw new PostNotFoundException("Post Not Found");
        }
        return postOptional.orElse(null);

    }


    @Override
    public PostsResponseDto searchPosts(Integer userId, String bodyText) {
        List<Post> postsByUserId = getPostsByUserId(userId);
        List<Post> postsByBodyText = getPostsByTextInBody(bodyText);
        List<Post> totalPosts = new ArrayList<>(postsByUserId);
        totalPosts.addAll(postsByBodyText);
        if(!postsByUserId.isEmpty() && !postsByBodyText.isEmpty()){
            totalPosts = postsByBodyText.stream().filter(post ->
                    Objects.equals(post.userId(), userId)
            ).toList();
        }
        if(!postsByUserId.isEmpty() && postsByBodyText.isEmpty() && bodyText != null){
            totalPosts = new ArrayList<>();
        }
        PostsResponseDto postDto = new PostsResponseDto();
        postDto.setCount(totalPosts.size());
        postDto.setResults(totalPosts);
        return postDto;
    }

    public List<Post> getPostsByUserId(Integer userId) {
        if(userId != null){
            User user = userService.getUserById(userId);
            List<Post> posts = postClient.findAll();
            List<Post> postsByUser = posts.stream().filter(post->
                    Objects.equals(post.userId(), userId)
            ).toList();
            return postsByUser;
        }
        return new ArrayList<>();

    }


    public List<Post> getPostsByTextInBody(String text) {
        if(text != null){
            List<Post> posts = postClient.findAll();
            List<Post> postsWithText = posts.stream().filter(post ->
                    post.body().contains(text)).toList();

            return postsWithText;
        }
        return new ArrayList<>();
    }
}
