package dev.seba.apiaref.dto.response;

import lombok.Data;

@Data
public class PostMetricsResponseDto {
    Integer postId;
    Integer commentCount;
    String postInfoPath;
}
