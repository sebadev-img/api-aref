package dev.seba.apiaref.controller;

import dev.seba.apiaref.dto.response.PostsResponseDto;
import dev.seba.apiaref.model.Post;
import dev.seba.apiaref.service.Impl.PostServiceImpl;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@WebMvcTest(controllers = PostController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostServiceImpl postService;

    @Test
    void testThatGetAllPostsReturnsPostDto() throws Exception {
        List<Post> data = List.of(
                new Post(1,1,"title","body"),
                new Post(2,1,"title","body")
        );
        PostsResponseDto postDto = new PostsResponseDto();
        postDto.setCount(data.size());
        postDto.setResults(data);
        when(postService.getAll()).thenReturn(postDto);
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/posts")
                .contentType(APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.count", CoreMatchers.is(data.size())));
    }

    @Test
    void testThatGetPostReturnsPost() throws Exception {
        Post data = new Post(2,1,"title","body");
        when(postService.getById(2)).thenReturn(data);
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/posts/2")
                .contentType(APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id",CoreMatchers.is(data.id())));
    }

    @Test
    void testThatSearchPostsByUserIdReturnsPostDto() throws Exception {
        List<Post> data = List.of(
                new Post(1,1,"title","body"),
                new Post(2,1,"title","body")
        );
        PostsResponseDto postDto = new PostsResponseDto();
        postDto.setCount(data.size());
        postDto.setResults(data);
        when(postService.getPostsByUserId(1)).thenReturn(postDto);
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/posts/search?userId=1")
                .contentType(APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.count", CoreMatchers.is(data.size())));
    }

    @Test
    void testThatSearchPostsByBodyTextReturnsPostDto() throws Exception {
        List<Post> data = List.of(
                new Post(2,1,"title","body text")
        );
        PostsResponseDto postDto = new PostsResponseDto();
        postDto.setCount(data.size());
        postDto.setResults(data);
        when(postService.getPostsByTextInBody("dy")).thenReturn(postDto);
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/posts/search?bodyText=dy")
                .contentType(APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.count", CoreMatchers.is(data.size())));
    }
}