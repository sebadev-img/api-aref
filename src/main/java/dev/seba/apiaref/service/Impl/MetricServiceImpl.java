package dev.seba.apiaref.service.Impl;

import dev.seba.apiaref.client.CommentRestClient;
import dev.seba.apiaref.client.PostRestClient;
import dev.seba.apiaref.client.UserRestClient;
import dev.seba.apiaref.dto.response.PostMetricsResponseDto;
import dev.seba.apiaref.dto.response.UserMetricsResponseDto;
import dev.seba.apiaref.model.Comment;
import dev.seba.apiaref.model.Post;
import dev.seba.apiaref.model.User;
import dev.seba.apiaref.service.IMetricService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetricServiceImpl implements IMetricService {

    private final PostServiceImpl postService;
    private final CommentRestClient commentClient;
    private final UserServiceImpl userService;

    public MetricServiceImpl(UserServiceImpl userService, CommentRestClient commentClient, PostServiceImpl postService){

        this.postService = postService;
        this.commentClient = commentClient;
        this.userService = userService;
    }
    @Override
    public UserMetricsResponseDto getUserMetrics(int userId) {
        User user = userService.getUserById(userId);
        List<Post> userPosts = postService.getPostsByUserId(userId);
        UserMetricsResponseDto metricDto = new UserMetricsResponseDto();
        metricDto.setUserId(userId);
        metricDto.setPostCount(userPosts.size());
        metricDto.setUserInfoPath("/api/users/"+userId);
        metricDto.setUserPostsPath("/api/posts/search?userId="+userId);
        return metricDto;
    }

    @Override
    public PostMetricsResponseDto getPostMetrics(int postId) {
        Post post = postService.getById(postId);
        List<Comment> postComments = commentClient.findCommentsByPostId(postId);
        PostMetricsResponseDto metricDto = new PostMetricsResponseDto();
        metricDto.setPostId(postId);
        metricDto.setCommentCount(postComments.size());
        metricDto.setPostInfoPath("/api/posts/"+postId);
        return metricDto;
    }
}
