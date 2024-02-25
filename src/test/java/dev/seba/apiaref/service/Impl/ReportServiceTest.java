package dev.seba.apiaref.service.Impl;

import dev.seba.apiaref.client.CommentRestClient;
import dev.seba.apiaref.client.PostRestClient;
import dev.seba.apiaref.dto.response.PostReportResponseDto;
import dev.seba.apiaref.model.Comment;
import dev.seba.apiaref.model.Post;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.anyOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.AdditionalMatchers.not;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReportServiceTest {
    @Mock
    private PostRestClient postClient;

    @Mock
    private CommentRestClient commentClient;

    @InjectMocks
    private ReportServiceImpl reportService;

    @Test
    void testThatGetTop10PostByCommentReturnsReportDto() {
        List<Post> postData = List.of(
                new Post(1,1,"title","body"),
                new Post(2,1,"title","body"),
                new Post(3,1,"title","body"),
                new Post(4,1,"title","body"),
                new Post(5,1,"title","body"),
                new Post(6,1,"title","body"),
                new Post(7,1,"title","body"),
                new Post(8,1,"title","body"),
                new Post(9,1,"title","body"),
                new Post(10,1,"title","body"),
                new Post(11,1,"title","body"),
                new Post(12,1,"title","body")
        );
        List<Comment> commentData = List.of(
                new Comment(1,1,"name","email","body"),
                new Comment(1,2,"name","email","body"),
                new Comment(1,3,"name","email","body"),

                new Comment(1,4,"name","email","body"),
                new Comment(1,4,"name","email","body"),

                new Comment(1,5,"name","email","body"),
                new Comment(1,6,"name","email","body"),

                new Comment(1,7,"name","email","body"),
                new Comment(1,7,"name","email","body"),
                new Comment(1,7,"name","email","body"),

                new Comment(1,8,"name","email","body"),
                new Comment(1,9,"name","email","body"),

                new Comment(1,10,"name","email","body"),
                new Comment(1,10,"name","email","body"),

                new Comment(1,11,"name","email","body"),
                new Comment(1,12,"name","email","body")
        );

        when(commentClient.findAll()).thenReturn(commentData);

        PostReportResponseDto postReport = reportService.getTop10PostByComment();
        assertEquals(10,postReport.getCount());
        assertEquals(3,postReport.getResults().get(0).getCommentCount());
        assertEquals(2,postReport.getResults().get(1).getCommentCount());
        assertEquals(2,postReport.getResults().get(2).getCommentCount());
        assertEquals(1,postReport.getResults().get(3).getCommentCount());
        assertEquals(1,postReport.getResults().get(9).getCommentCount());
    }
}