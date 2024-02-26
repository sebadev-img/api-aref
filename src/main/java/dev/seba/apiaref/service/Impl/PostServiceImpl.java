package dev.seba.apiaref.service.Impl;

import dev.seba.apiaref.client.PostRestClient;
import dev.seba.apiaref.client.UserRestClient;
import dev.seba.apiaref.dto.response.PostsResponseDto;
import dev.seba.apiaref.model.Post;
import dev.seba.apiaref.model.User;
import dev.seba.apiaref.service.IPostService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements IPostService {

    private final PostRestClient postClient;
    private final UserRestClient userClient;

    public PostServiceImpl(PostRestClient postRestClient, UserRestClient userClient){
        this.postClient = postRestClient;
        this.userClient = userClient;
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
            User user = userClient.findById(userId);
            List<Post> posts = postClient.findByUserId(userId);
            return posts;
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
