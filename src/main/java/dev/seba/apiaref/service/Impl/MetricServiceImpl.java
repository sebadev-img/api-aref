package dev.seba.apiaref.service.Impl;

import dev.seba.apiaref.client.PostRestClient;
import dev.seba.apiaref.dto.response.UserMetricsResponseDto;
import dev.seba.apiaref.model.Post;
import dev.seba.apiaref.service.IMetricService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetricServiceImpl implements IMetricService {

    private final PostRestClient postClient;

    public MetricServiceImpl(PostRestClient postClient){
        this.postClient = postClient;
    }
    @Override
    public UserMetricsResponseDto getUserMetrics(int userId) {
        List<Post> userPosts = postClient.findByUserId(userId);
        UserMetricsResponseDto metricDto = new UserMetricsResponseDto();
        metricDto.setUserId(userId);
        metricDto.setPostCount(userPosts.size());
        metricDto.setUserInfoPath("/api/users/"+userId);
        metricDto.setUserPostsPath("/api/posts/search?userId="+userId);
        return metricDto;
    }
}
