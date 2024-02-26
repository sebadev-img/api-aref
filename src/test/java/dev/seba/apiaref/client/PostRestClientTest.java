package dev.seba.apiaref.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.seba.apiaref.model.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(PostRestClient.class)
class PostRestClientTest {

    @Autowired
    MockRestServiceServer server;
    @Autowired
    PostRestClient postClient;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testThatFindAllReturnsListOfPost() throws JsonProcessingException {
        List<Post> data = List.of(
                new Post(1,1,"test title 1","test body 1"),
                new Post(2,1,"test title 2", "test body 2")
        );
        server.expect(requestTo("https://jsonplaceholder.typicode.com/posts"))
                .andRespond(withSuccess(objectMapper.writeValueAsString(data), MediaType.APPLICATION_JSON));
        List<Post> posts = postClient.findAll();
        assertEquals(2,posts.size());
    }

    @Test
    void TestThatFindByIdReturnsPost() throws JsonProcessingException {
        Post data = new Post(2,1,"test title","test body");
        server.expect(requestTo("https://jsonplaceholder.typicode.com/posts/2"))
                .andRespond(withSuccess(objectMapper.writeValueAsString(data), MediaType.APPLICATION_JSON));
        Post post = postClient.findById(2);
        assertEquals(2,post.id());
    }

    @Test
    void TestThatFindByUserIdResturnsListOfPost() throws JsonProcessingException {
        List<Post> data = List.of(
                new Post(1,1,"test title 1","test body 1"),
                new Post(2,1,"test title 2", "test body 2")
        );
        server.expect(requestTo("https://jsonplaceholder.typicode.com/posts?userId=1"))
                .andRespond(withSuccess(objectMapper.writeValueAsString(data), MediaType.APPLICATION_JSON));
        List<Post> posts = postClient.findByUserId(1);
        assertEquals(2,posts.size());
    }
}