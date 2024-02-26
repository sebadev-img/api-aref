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
    void testThatGetPostsByUserIdReturnsListOfPost() {
        List<Post> data = List.of(
                new Post(1,1,"title","body"),
                new Post(2,1,"title","body")
        );
        User user = mock(User.class);
        when(userClient.findById(1)).thenReturn(user);
        when(postClient.findByUserId(1)).thenReturn(data);
        List<Post> result = postService.getPostsByUserId(1);
        assertEquals(2, result.size());
    }

    @Test
    void testThatGetPostsByTextInBodyReturnsListOfPost() {
        List<Post> data = List.of(
                new Post(1,1,"title","body"),
                new Post(2,1,"title","body"),
                new Post(3,2,"title","text body")

        );
        when(postClient.findAll()).thenReturn(data);
        List<Post> result = postService.getPostsByTextInBody("xt b");
        assertEquals(1, result.size());
    }

    @Test
    void testThatSearchPostsReturnsPostDto(){
        List<Post> dataUserId = List.of(
                new Post(1,1,"title","body"),
                new Post(2,1,"title","body")
        );
        List<Post> dataBodyText = List.of(
                new Post(1,1,"title","body"),
                new Post(2,1,"title","text body"),
                new Post(3,2,"title","text body")

        );

        User user = mock(User.class);
        when(userClient.findById(1)).thenReturn(user);
        when(postClient.findByUserId(1)).thenReturn(dataUserId);

        when(postClient.findAll()).thenReturn(dataBodyText);

        int userId = 1;
        int userIdNotFound = 3;

        String bodyText = "xt b";
        String bodyTextNotFound = "sss";

        PostsResponseDto result1 = postService.searchPosts(userId,bodyText);
        assertEquals(1,result1.getCount());

        PostsResponseDto result2 = postService.searchPosts(userIdNotFound,bodyText);
        assertEquals(2,result2.getCount());

        PostsResponseDto result3 = postService.searchPosts(userId,bodyTextNotFound);
        assertEquals(0,result3.getCount());

        PostsResponseDto result4 = postService.searchPosts(userIdNotFound,bodyTextNotFound);
        assertEquals(0,result4.getCount());

    }
}