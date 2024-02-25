package dev.seba.apiaref.dto.response;

import lombok.Data;

@Data
public class UserMetricsResponseDto {
    private Integer userId;
    private Integer postCount;
    private String userInfoPath;
    private String userPostsPath;
}
