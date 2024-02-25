package dev.seba.apiaref.service.Impl;

import dev.seba.apiaref.client.PostRestClient;
import dev.seba.apiaref.client.UserRestClient;
import dev.seba.apiaref.dto.response.PostsResponseDto;
import dev.seba.apiaref.model.Post;
import dev.seba.apiaref.model.User;
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
class PostServiceImplTest {

    @Mock
    private PostRestClient postClient;
    @Mock
    private UserRestClient userClient;

    @InjectMocks
    private PostServiceImpl postService;

    @Test
    void testThatGetAllReturnsPostDto() {
        List<Post> data = List.of(
                new Post(1,1,"title","body"),
                new Post(2,1,"title","body")
        );
        when(postClient.findAll()).thenReturn(data);
        PostsResponseDto postDto = postService.getAll();
        assertEquals(2, postDto.getCount());
    }

    @Test
    void testThatGetByIdReturnsPost() {
        Post data = new Post(2,1,"title","body");
        when(postClient.findById(2)).thenReturn(data);
        Post post = postService.getById(2);
        assertEquals(2,post.id());
    }

    @Test
    void testThatGetPostsByUserIdReturnsPostDto() {
        List<Post> data = List.of(
                new Post(1,1,"title","body"),
                new Post(2,1,"title","body")
        );
        User user = mock(User.class);
        when(userClient.findById(1)).thenReturn(user);
        when(postClient.findByUserId(1)).thenReturn(data);
        PostsResponseDto postDto = postService.getPostsByUserId(1);
        assertEquals(2, postDto.getCount());
    }

    @Test
    void testThatGetPostsByTextInBodyReturnsPostDto() {
        List<Post> data = List.of(
                new Post(1,1,"title","body"),
                new Post(2,1,"title","body"),
                new Post(3,2,"title","text body")

        );
        when(postClient.findAll()).thenReturn(data);
        PostsResponseDto postDto = postService.getPostsByTextInBody("xt b");
        assertEquals(1, postDto.getCount());
    }
}