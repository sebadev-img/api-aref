package dev.seba.apiaref.service.Impl;

import dev.seba.apiaref.client.CommentRestClient;
import dev.seba.apiaref.client.PostRestClient;
import dev.seba.apiaref.client.UserRestClient;
import dev.seba.apiaref.dto.response.PostMetricsResponseDto;
import dev.seba.apiaref.dto.response.UserMetricsResponseDto;
import dev.seba.apiaref.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MetricServiceImplTest {

    @Mock
    private PostServiceImpl postService;

    @Mock
    private CommentServiceImpl commentService;

    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private MetricServiceImpl metricService;

    @Test
    void testThatGetUserMetricsReturnsMetricDto() {
        List<Post> data = List.of(
                new Post(1,3,"title","body"),
                new Post(2,3,"title","body")
        );
        User user = new User(
                3,
                "test name",
                "username3",
                "email3",
                new Address(
                        "street",
                        "suite",
                        "city",
                        "zipcode",
                        new Geo(2.0,2.0)
                ),
                "phone",
                "website",
                new Company(
                        "name",
                        "phrase",
                        "bs"
                ));
        when(userService.getUserById(3)).thenReturn(user);
        when(postService.getPostsByUserId(3)).thenReturn(data);
        UserMetricsResponseDto metricDto = metricService.getUserMetrics(3);
        assertEquals(2, metricDto.getPostCount());
    }

    @Test
    void testThatGetPostMetricsReturnsMetricDto(){
        List<Comment> data = List.of(
                new Comment(1,1,"name","email","body"),
                new Comment(2,1,"name","email","body")
        );
        Post post = new Post(1,1,"title","body");
        when(postService.getById(1)).thenReturn(post);
        when(commentService.getCommentsByPostId(1)).thenReturn(data);
        PostMetricsResponseDto metricDto = metricService.getPostMetrics(1);
        assertEquals(2,metricDto.getCommentCount());
    }
}