package dev.seba.apiaref.service.Impl;

import dev.seba.apiaref.client.CommentRestClient;
import dev.seba.apiaref.model.Comment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {
    @Mock
    private CommentRestClient commentClient;

    @InjectMocks
    private CommentServiceImpl commentService;

    @Test
    void testThatGetCommentsByPostIdReturnListOfComments() {
        List<Comment> data = List.of(
                new Comment(1,1,"name","email","body"),
                new Comment(2,1,"name","email","body")
        );
        when(commentClient.findAll()).thenReturn(data);
        List<Comment> result = commentService.getCommentsByPostId(1);
        assertEquals(2,result.size());
    }
}