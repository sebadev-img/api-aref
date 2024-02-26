package dev.seba.apiaref.service;

import dev.seba.apiaref.dto.response.PostMetricsResponseDto;
import dev.seba.apiaref.dto.response.UserMetricsResponseDto;

public interface IMetricService {
    UserMetricsResponseDto getUserMetrics(int userId);
    PostMetricsResponseDto getPostMetrics(int postId);
}
