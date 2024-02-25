package dev.seba.apiaref.service.Impl;

import dev.seba.apiaref.client.PostRestClient;
import dev.seba.apiaref.dto.response.UserMetricsResponseDto;
import dev.seba.apiaref.model.Post;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MetricServiceImplTest {

    @Mock
    private PostRestClient postClient;

    @InjectMocks
    private MetricServiceImpl metricService;

    @Test
    void testThatGetUserMetricsReturnsMetricDto() {
        List<Post> data = List.of(
                new Post(1,1,"title","body"),
                new Post(2,1,"title","body")
        );
        when(postClient.findByUserId(1)).thenReturn(data);
        UserMetricsResponseDto metricDto = metricService.getUserMetrics(1);
        assertEquals(2, metricDto.getPostCount());
    }
}