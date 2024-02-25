package dev.seba.apiaref.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.seba.apiaref.model.Comment;
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

@RestClientTest(CommentRestClient.class)
class CommentRestClientTest {
    @Autowired
    MockRestServiceServer server;
    @Autowired
    CommentRestClient commentClient;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testThatFindCommentsByPostIdReturnListOfComments() throws JsonProcessingException {
        List<Comment> data = List.of(
                new Comment(1,1,"name","email","body"),
                new Comment(2,1,"name","email","body")
        );
        server.expect(requestTo("https://jsonplaceholder.typicode.com/comments?postId=1"))
                .andRespond(withSuccess(objectMapper.writeValueAsString(data), MediaType.APPLICATION_JSON));
        List<Comment> comments = commentClient.findCommentsByPostId(1);
        assertEquals(2,comments.size());
    }
}