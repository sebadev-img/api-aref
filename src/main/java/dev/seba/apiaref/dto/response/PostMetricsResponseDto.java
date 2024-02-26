package dev.seba.apiaref.dto.response;

import lombok.Data;

@Data
public class PostMetricsResponseDto {
    private Integer postId;
    private Integer commentCount;
    private String postInfoPath;
}
