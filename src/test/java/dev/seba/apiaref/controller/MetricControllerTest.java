package dev.seba.apiaref.controller;

import dev.seba.apiaref.dto.response.PostMetricsResponseDto;
import dev.seba.apiaref.dto.response.UserMetricsResponseDto;
import dev.seba.apiaref.model.Comment;
import dev.seba.apiaref.model.Post;
import dev.seba.apiaref.service.Impl.MetricServiceImpl;
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

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@WebMvcTest(controllers = MetricController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class MetricControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MetricServiceImpl metricService;

    @Test
    void testThatGetUserMetricReturnMetricDto() throws Exception {
        List<Post> data = List.of(
                new Post(1,1,"title","body"),
                new Post(2,1,"title","body")
        );
        UserMetricsResponseDto metricDto = new UserMetricsResponseDto();
        metricDto.setUserId(1);
        metricDto.setPostCount(2);
        when(metricService.getUserMetrics(1)).thenReturn(metricDto);
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/metrics/users/1")
                .contentType(APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.postCount", CoreMatchers.is(data.size())));

    }

    @Test
    void testThatPostMetricReturnsMetricDto() throws Exception {
        List<Comment> data = List.of(
                new Comment(1,1,"name","email","body"),
                new Comment(2,1,"name","email","body")
        );
        PostMetricsResponseDto metricDto = new PostMetricsResponseDto();
        metricDto.setPostId(1);
        metricDto.setCommentCount(data.size());
        when(metricService.getPostMetrics(1)).thenReturn(metricDto);
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/metrics/posts/1")
                .contentType(APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.commentCount", CoreMatchers.is(data.size())));

    }
}